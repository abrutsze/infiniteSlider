package com.example.task.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.entity.localmodels.DataModel
import com.example.task.R
import com.example.task.fragment.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var adapter: TestPagerAdapter
    private val homeViewModel: HomeViewModel by viewModel()
    private var itemList = mutableListOf<DataModel>()
    private var fakeSize = 0
    private var currentPosition = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initView()
    }

    private fun initView() {
        adapter = TestPagerAdapter(itemList)
        viewPager.adapter = adapter
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    if (currentPosition == 0) {
                        title.text=itemList[fakeSize - 2].title
                        viewPager.setCurrentItem(fakeSize - 2, false);
                    } else if (currentPosition == fakeSize - 1) {
                        title.text=itemList[1].title
                        viewPager.setCurrentItem(1, false);
                    }
                } else if (state == ViewPager2.SCROLL_STATE_DRAGGING && currentPosition == fakeSize) {
                    viewPager.setCurrentItem(2, false);
                }
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                title.text=itemList[position].title
                currentPosition = position
            }
        })

    }

    private fun initViewModel() {
        homeViewModel.getDataModelDataList.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                fakeSize = it.size + 2;
                itemList = adapter.transformListAndAddTwo(it)
                adapter.updateList(itemList)
                viewPager.setCurrentItem(2, false)
            }

        })
        homeViewModel.loadingData.observe(viewLifecycleOwner, Observer {
            if (it)
                vLoadingData.visibility = View.VISIBLE
            else
                vLoadingData.visibility = View.GONE
        })
        homeViewModel.errorNullData.observe(viewLifecycleOwner, Observer {
            context?.run {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
            vLoadingData.visibility = View.GONE
        })
    }
}
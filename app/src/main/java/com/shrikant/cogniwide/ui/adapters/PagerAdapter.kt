package com.shrikant.cogniwide.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.shrikant.cogniwide.R
import com.shrikant.cogniwide.ui.history.HistoryFragment
import com.shrikant.cogniwide.ui.movie.list.MovieListFragment

class PagerAdapter(fm: FragmentManager, private val tabText: Array<String>) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    fun getTabView(position: Int, context: Context): View {
        val v = LayoutInflater.from(context).inflate(R.layout.item_tab, null)
        val tv = v.findViewById<TextView>(R.id.tvTitle2)
        tv.text = tabText[position]
        return v
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                return MovieListFragment()
            }
            1 -> {
                return HistoryFragment()
            }
            else -> Fragment()
        }
    }

    override fun getCount(): Int {
        return tabText.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabText[position]
    }


}

package br.com.brunoalmeida.movieapp.main.view

import android.app.SearchManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import br.com.brunoalmeida.movieapp.R
import br.com.brunoalmeida.movieapp.commons.RxBus
import br.com.brunoalmeida.movieapp.commons.mvp.BaseMvpActivity
import br.com.brunoalmeida.movieapp.main.presenter.MainContract
import br.com.brunoalmeida.movieapp.main.presenter.MainPresenter
import br.com.brunoalmeida.movieapp.mymovies.view.MyMoviesFragment
import br.com.brunoalmeida.movieapp.search.view.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMvpActivity<MainContract.View, MainPresenter>(), MainContract.View {

    var onRequesting = false

    override var mPresenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(main_toolbar)
        val tabAdapter = TabAdapter(supportFragmentManager)

        main_toolbar.setTitleTextColor(getContext().resources.getColor(R.color.white))
        tabAdapter.add(MyMoviesFragment(), getString(R.string.my_movies))
        tabAdapter.add(SearchFragment(), getString(R.string.search_movies))

        main_pager.adapter = tabAdapter
        main_pager.offscreenPageLimit = 0


        main_tabs.setupWithViewPager(main_pager)

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_my_movies, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = getString(R.string.get_movie_name)

        searchView.setBackgroundColor(resources.getColor(R.color.white))
        searchView.setOnClickListener {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                RxBus.searchSubject.onNext(query)
                return false
            }
        })

        return true
    }

}

class TabAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    val fragments: MutableList<Fragment> = ArrayList()
    val titles: MutableList<String> = ArrayList()

    fun add(frag: Fragment, title: String) {
        fragments.add(frag)
        titles.add(title)
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

}
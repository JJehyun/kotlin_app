package com.example.study01

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.study01.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!



    private val FragmentOne = FragmentHOME()
    private val FragmentTwo = Fragmentschool()
    private val FragmentThree = FragmentMOVIE()
    var str : String? = null
    var strtwo : String? = null
    var strthree : String? = null
    var strfour : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lateinit var profileAdapter: ProfileAdapter
        val datas = mutableListOf<ProfileData>()


        fun changeFragment(fragment: Fragment) {
            supportFragmentManager .beginTransaction()
                .replace(R.id.Fla, fragment)
                .commit() }                                                                 //함수 선언 될 때 마다 Fragment 변경 되는 함수
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val url = "http://www.cgv.co.kr/movies/"
            val doc: Document = Jsoup.connect(url).get()
            val element: Elements = doc.select("#contents > div.wrap-movie-chart > div.sect-movie-chart > ol:nth-child(2) > li:nth-child(1) > div.box-contents > a > strong")

            val elementtwo: Elements = doc.select("#contents > div.wrap-movie-chart > div.sect-movie-chart > ol:nth-child(2) > li:nth-child(2) > div.box-contents > a > strong")

            val elementthree: Elements = doc.select("#contents > div.wrap-movie-chart > div.sect-movie-chart > ol:nth-child(2) > li:nth-child(3) > div.box-contents > a > strong")

            val elementfour: Elements = doc.select("#contents > div.wrap-movie-chart > div.sect-movie-chart > ol:nth-child(3) > li:nth-child(1) > div.box-contents > a > strong")

            str = element.text()
            strtwo= elementtwo.text()
            strthree = elementthree.text()
            strfour =elementfour.text()



            val data = arrayOf((str).toString(),(strtwo).toString(),(strthree).toString(),(strfour).toString()) //들어갈 문구 첫번째 aa, 두번째 bb ,세번째 cc, 네번째 dd (슬라이드에 넣을 문구)
            binding.pagertwo.adapter = VPAtwo(data) //어댑터에 데이터 삽입 여기서 선언한 class 생성해야함
            binding.pagertwo.orientation = ViewPager2.ORIENTATION_HORIZONTAL //가로로 넘기기 설정
            binding.pagertwo.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            })
        }

        fun initNavigationBar() { binding.navBottom.run { setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.first -> {
                    changeFragment(FragmentOne)                }
                R.id.second -> {
                    changeFragment(FragmentTwo)
                }
                R.id.third -> {
                    changeFragment(FragmentThree)
                }
            }
            true
        }
            selectedItemId = R.id.second
        }
        }


        
        // 여기 까지 bottom nav 구현 Fragment 까지

        initNavigationBar()






        fun initRecycler() {
            profileAdapter = ProfileAdapter(this)
            binding.rvProfile.adapter = profileAdapter


            datas.apply {
                add(ProfileData(img = R.drawable.jehyunstar, name = "free board", age = "첫 게시글"))
                add(ProfileData(img = R.drawable.jehyunstar, name = "free board", age = "두번째 게시글"))
                add(ProfileData(img = R.drawable.jehyunstar, name = "free board", age = "세번째 게시글"))
                add(ProfileData(img = R.drawable.jehyunstar, name = "free board", age = "네번째 게시글"))
                add(ProfileData(img = R.drawable.jehyunstar, name = "free board", age = "다섯 게시글"))
                add(ProfileData(img = R.drawable.jehyunstar, name = "free board", age = "여섯 게시글"))

                profileAdapter.datas = datas
                profileAdapter.notifyDataSetChanged()

            }
        }

        initRecycler()








    }



    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }
}


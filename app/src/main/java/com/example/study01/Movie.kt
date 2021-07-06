package com.example.study01

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


class Movie {

    var url = "http://www.cgv.co.kr/movies/"
    var doc: Document = Jsoup.connect(url).get()
    var element: Elements = doc.select("div.sect-movie-chart")
    var str = element.text()
    fun asd(){
        println(str)
    }


}
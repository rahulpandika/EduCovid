package com.capstoneproject.csd190.utils

import com.capstoneproject.csd190.model.Question

object QuizData {
    const val score: String = "score"

    fun getQuestion(): ArrayList<Question> {
        val ques: ArrayList<Question> = arrayListOf()
        val q1 = Question(
            "Nama lain dari virus Corona adalah?",
            1,
            "Covid-19",
            "Covid-20",
            "Covid",
            "Virus Corona",
            1,
        )
        val q2 = Question(
            "Salah satu langkah untuk mencegah penularan covid-19 adalah dengan 3M, apa itu 3M?",
            1,
            "Melangkah, mencuci tangan, memasak",
            "Mencuci tangan, menjaga jarak, memakai masker",
            "Mencuci tangan, memakai masker, mandi",
            "Memakai masker, mencuci tangan, menghindar",
            2,
        )
        val q3 = Question(
            "Lockdown pertama kali berlangsung selama?",
            2,
            "3 Minggu",
            "14 Hari",
            "7 Hari",
            "3 Tahun",
            2
        )
        val q4 = Question(
            "Wabah COVID-19 muncul di negara ... pada awal tahun 2020.",
            3,
            "China",
            "Italia",
            "Amerika",
            "Indonesia",
            1
        )
        val q5 = Question(
            "COVID-19 bisa masuk melalui anggota-anggota tubuh di bawah ini, kecuali ...",
            4,
            "Mata",
            "Hidung",
            "Mulut",
            "Telinga",
            4
        )

        ques.add(q1)
        ques.add(q2)
        ques.add(q3)
        ques.add(q4)
        ques.add(q5)

        return ques
    }
}
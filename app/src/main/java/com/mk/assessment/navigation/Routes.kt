package com.mk.assessment.navigation

enum class Routes {
    HomeScreen,
    LoginScreen,
    ;

    companion object {
        val disabledScreens = listOf<Routes>()

        private var allScreenList: List<Routes> = entries

        var allScreens: List<Routes>
            get() = allScreenList
            set(value) {
                allScreenList = value
            }
    }
}

package com.example.recyclerview

import android.app.Application
import android.util.Log
import timber.log.Timber
import timber.log.Timber.*


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())

//        if (BuildConfig.DEBUG) {
//        } else {
//            Timber.plant(CrashReportingTree())
//        }

    }

//    /** A tree which logs important information for crash reporting.  */
//    class CrashReportingTree : Tree() {
//        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
//            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
//                return
//            }
////            FakeCrashLibrary.log(priority, tag, message)
////            if (t != null) {
////                if (priority == Log.ERROR) {
////                    FakeCrashLibrary.logError(t)
////                } else if (priority == Log.WARN) {
////                    FakeCrashLibrary.logWarning(t)
////                }
////            }
//        }
//    }
}
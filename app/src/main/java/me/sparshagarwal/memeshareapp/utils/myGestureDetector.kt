package me.sparshagarwal.memeshareapp.utils

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent

class myGestureDetector : GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener {

    override fun onDown(p0: MotionEvent?): Boolean {
        Log.i("Action", "onDown")
        return true
    }

    override fun onShowPress(p0: MotionEvent?) {
        TODO("Not yet implemented")
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        Log.i("Action", "onScroll")
        return true
    }

    override fun onLongPress(p0: MotionEvent?) {
        TODO("Not yet implemented")
    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        Log.i("Action", "onFling")
        return true
    }

    override fun onSingleTapConfirmed(p0: MotionEvent?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onDoubleTap(p0: MotionEvent?): Boolean {
        Log.i("Action", "onDoubleTap")
        return true
    }

    override fun onDoubleTapEvent(p0: MotionEvent?): Boolean {
        TODO("Not yet implemented")
    }
}
package cu.jesusd0897.wallkube.adapter

interface OnScrollListener {
    fun onTopReached(position: Int)
    fun onCenterReached(position: Int)
    fun onBottomReached(position: Int)
}
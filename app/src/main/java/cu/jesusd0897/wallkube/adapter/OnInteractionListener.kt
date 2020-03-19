package cu.jesusd0897.wallkube.adapter

interface OnInteractionListener<M> {
    fun onClick(position: Int, item: M)
    fun onLongClick(position: Int, item: M)
}
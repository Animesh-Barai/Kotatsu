package org.koitharu.kotatsu.ui.reader.wetoon

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koitharu.kotatsu.ui.reader.base.OnBoundsScrollListener

class ListPaginationListener(
	private val offset: Int,
	private val listener: OnBoundsScrollListener
) : RecyclerView.OnScrollListener() {

	private var firstItemId: Long = 0
	private var lastItemId: Long = 0

	override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
		val adapter = recyclerView.adapter ?: return
		val layoutManager = (recyclerView.layoutManager as? LinearLayoutManager) ?: return
		val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()
		val lastVisiblePosition = layoutManager.findLastVisibleItemPosition()
		val itemCount = adapter.itemCount
		if (itemCount == 0) {
			return
		}
		if (lastVisiblePosition >= itemCount - offset && adapter.getItemId(itemCount - 1) != lastItemId) {
			lastItemId = adapter.getItemId(itemCount - 1)
			listener.onScrolledToEnd()
		} else if (firstVisiblePosition <= offset && adapter.getItemId(0) != firstItemId) {
			firstItemId = adapter.getItemId(0)
			listener.onScrolledToStart()
		}
	}
}
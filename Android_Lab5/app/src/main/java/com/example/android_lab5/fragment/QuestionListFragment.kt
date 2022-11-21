package com.example.android_lab5.fragment

import Item
import ItemRepository
import ItemService
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_lab5.R
import com.example.android_lab5.adapter.DataAdapter
import com.example.android_lab5.adapter.OnItemClickListener
import com.example.android_lab5.viewmodel.ItemViewModel

class QuestionListFragment : Fragment(), OnItemClickListener {
    val itemViewModel: ItemViewModel by activityViewModels()

    var list: List<Item> = ItemService(ItemRepository()).selectRandomItems()
    lateinit var adapter: DataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.questionRecyclerView)

        adapter = DataAdapter(list, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.setHasFixedSize(true)
    }

    override fun onItemClick(position: Int) {
        val clickedItem : Item = list[position]
        // clickedItem.question = "Clicked"
        adapter.notifyItemChanged(position)

        // set item for DetailScreen
        itemViewModel.item = clickedItem

        // open DetailFragment
        findNavController().navigate(R.id.action_questionListFragment_to_questionDetailFragment)
    }
}
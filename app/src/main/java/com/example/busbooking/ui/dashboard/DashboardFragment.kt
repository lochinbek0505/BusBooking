package com.example.busbooking.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.busbooking.adapters.HistoryAdapter
import com.example.busbooking.databinding.FragmentDashboardBinding
import com.example.busbooking.utils.TicketDatabaseHelper

class DashboardFragment : Fragment() {

    private var binding: FragmentDashboardBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding!!.root

        val db=TicketDatabaseHelper(requireActivity())
        val array=db.getAllTickets()

        val adapter=HistoryAdapter(array)

        binding!!.fdRecycler.adapter=adapter


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
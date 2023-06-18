package org.d3if3068.assesment001

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3068.assesment001.databinding.FragmentListrumusBinding
import org.d3if3068.assesment001.db.ConverterDb
import org.d3if3068.assesment001.network.ApiStatus
import org.d3if3068.assesment001.network.SuhuApi
import org.d3if3068.assesment001.ui.Converter.ConverterViewModel
import org.d3if3068.assesment001.ui.Converter.ConverterViewModelFactory

class TermometerFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyView: TextView
    private lateinit var termometerAdapter: TermometerAdapter
    private lateinit var binding: FragmentListrumusBinding

    private val viewModel: ConverterViewModel by lazy {
        val db = ConverterDb.getInstance(requireContext())
        val factory = ConverterViewModelFactory(db.dao)
        ViewModelProvider(this, factory).get(ConverterViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListrumusBinding.inflate(inflater, container, false)
        val view = binding.root

        recyclerView = view.findViewById(R.id.recyclerView2)
        emptyView = view.findViewById(R.id.emptyView2)

        termometerAdapter = TermometerAdapter()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = termometerAdapter
        }

        fetchData()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getStatus().observe(viewLifecycleOwner) {
            updateProgress(it)
        }

        viewModel.scheduleUpdater(requireActivity().application)
    }

    private fun fetchData() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = withContext(Dispatchers.IO) {
                    SuhuApi.service.getConv()
                }

                if (response.isNotEmpty()) {
                    termometerAdapter.setData(response)
                    recyclerView.visibility = View.VISIBLE
                    emptyView.visibility = View.GONE
                } else {
                    recyclerView.visibility = View.GONE
                    emptyView.visibility = View.VISIBLE
                }
            } catch (e: Exception) {
                recyclerView.visibility = View.GONE
                emptyView.visibility = View.VISIBLE
            }
        }
    }



    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    requestNotificationPermission()
                    }
            }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestNotificationPermission() {
        if (ActivityCompat.checkSelfPermission( requireContext(), Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions( requireActivity(),
                arrayOf(Manifest.permission.POST_NOTIFICATIONS), MainActivity.PERMISSION_REQUEST_CODE
            )
        }
    }
}

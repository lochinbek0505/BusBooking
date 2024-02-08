package com.example.busbooking.ui.home

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.busbooking.HomeActivity
import com.example.busbooking.PlaceActivity
import com.example.busbooking.ReysActivity
import com.example.busbooking.SelectRegionActivity
import com.example.busbooking.databinding.FragmentHomeBinding
import java.util.Locale

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val PREF_NAME1 = "my_preferences1"
    private val PREF_NAME2 = "my_preferences2"

    private val binding get() = _binding!!
    var date = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val list2 = getStringsFromPreferences("Vil", "reg", PREF_NAME1)
        val vil = list2.get(0)
        val reg = list2.get(1)

        if (vil != "" && reg != "") {

            _binding!!.tvFrom.text = "$vil , $reg"

        }

//        val vil2 = requireActivity().intent.getStringExtra("Vil3")
//        val reg2= requireActivity().intent.getStringExtra("reg3")

        val list3 = getStringsFromPreferences("Vil3", "reg3", PREF_NAME2)
        val vil2 = list3.get(0)
        val reg2 = list3.get(1)

        if (vil2 != "" && reg2 != "") {

            _binding!!.tvTo.text = "$vil2 , $reg2"

        }

        _binding!!.cvDate.setOnClickListener {

            showDatePickerDialog()

        }
        _binding!!.cvFrom.setOnClickListener {

            startActivity(Intent(requireActivity(), SelectRegionActivity::class.java))
//            requireActivity().finish()

        }

        _binding!!.cvTo.setOnClickListener {


            if (!vil.isNullOrEmpty() && !reg.isNullOrEmpty()) {


                val intent = Intent(requireActivity(), SelectRegionActivity::class.java)
                intent.putExtra("Vil2", vil)
                intent.putExtra("reg2", reg)
                startActivity(intent)
//                requireActivity().finish()
            } else {

                Toast.makeText(
                    requireActivity(),
                    "Iltimos avval qayerdan borishni tanlang!",
                    Toast.LENGTH_LONG
                ).show()

            }

        }


        _binding!!.cvSearch.setOnClickListener {

            if (!date.isNullOrEmpty() && vil != "" && reg != "" && vil2 != "" && reg2 != "") {
                val arrayList = ArrayList<String>()
                arrayList.add(vil)
                arrayList.add(reg)
                arrayList.add(vil2)
                arrayList.add(reg2)
                arrayList.add(date)
                val intent = Intent(requireActivity(), ReysActivity::class.java)
                intent.putStringArrayListExtra("arrayList", arrayList)

                startActivity(intent)

            } else {

                Toast.makeText(requireActivity(), "Iltimos maydonni to'liq to'ldiring", Toast.LENGTH_LONG).show()


            }

        }
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

//                    clear("Vil", "reg", "my_preferences")

                    requireActivity().finishAffinity()
                }
            })
    }


    fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireActivity(),
            { _, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)

                val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                val formattedDate = sdf.format(selectedDate.time)
                date = formattedDate
                _binding!!.tvDate.text = date


            },

            currentYear,
            currentMonth,
            currentDay
        )


        datePickerDialog.show()
    }

    private fun getStringsFromPreferences(
        key1: String,
        key2: String,
        PREF_NAME: String
    ): ArrayList<String> {

        val list1 = ArrayList<String>()

        val sharedPreferences =
            requireContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val string1 = sharedPreferences.getString(key1, "") ?: ""
        val string2 = sharedPreferences.getString(key2, "") ?: ""

        list1.add(string1)
        list1.add(string2)

        return list1
    }

    private fun clear(key1: String, key2: String, PREF_NAME: String) {
        val sharedPreferences =
            requireContext().getSharedPreferences("${PREF_NAME}1", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("${key1}", "")
        editor.putString("${key2}", "")
        editor.apply()

        val sharedPreferences2 =
            requireContext().getSharedPreferences("${PREF_NAME}2", Context.MODE_PRIVATE)
        val editor2 = sharedPreferences2.edit()
        editor2.putString("${key1}3", "")
        editor2.putString("${key2}3", "")
        editor2.apply()
    }


    override fun onDestroyView() {
        super.onDestroyView()

//        clear("Vil", "reg", "my_preferences")


        _binding = null
    }
}
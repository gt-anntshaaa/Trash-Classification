package com.example.trashclassification.presentation.view.classify

import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.trashclassification.R
import com.example.trashclassification.data.model.Garbage
import com.example.trashclassification.databinding.FragmentClassificationBinding
import com.example.trashclassification.presentation.common.resources.UIState
import com.example.trashclassification.presentation.common.util.Constant.IMAGE_SIZE
import com.example.trashclassification.presentation.common.util.helper.imageProcessing
import com.example.trashclassification.presentation.common.util.show
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Integer.min


@AndroidEntryPoint
class ClassificationFragment : Fragment() {
    private var _binding: FragmentClassificationBinding? = null
    private val binding get() = _binding!!

    private val uiViewModel: UIClassifyViewModel by viewModels()
    private val classificationViewModel: ClassificationViewModel by viewModels()


    // pick image from galeri
    private val imageFromGaleri = registerForActivityResult(ActivityResultContracts.GetContent()){
        // handle the returned uri
        it?.let { uri ->
            classificationViewModel.setImage(uri)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClassificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFab()
        observeUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        private const val TAG = "ClassificationFragment::class"
    }


    private fun observeUI() {
        // observe UI FAB
        uiViewModel.fabIsAllVisible.observe(viewLifecycleOwner, Observer {isVisible->
            if (isVisible){
                showView()
            }else{
                hideView()
            }

        })

        // observe UI imageView
        classificationViewModel.image.observe(viewLifecycleOwner, Observer { uri ->
            if (uri != null){
                binding.images.setImageURI(uri)
                binding.images.show()
                binding.resultBtn.show()

                // uji coba process classification
                val imageBitmap = imageProcessing(requireContext(), uri)


                //binding.resultBtn.setOnClickListener { findNavController().navigate(ClassificationFragmentDirections.actionClassificationToResult()) }
                binding.resultBtn.setOnClickListener { onClassification(imageBitmap) }

            }else{
                Log.e(TAG, "observeUI: image uri is a null", )
            }
        })

        classificationViewModel.classification.observe(viewLifecycleOwner, Observer {uiState ->
            when(uiState){
                is UIState.Loading -> {}
                is UIState.Success -> {
                    //val data = Garbage(name = uiState.data, description = res)
                    Toast.makeText(requireContext(), uiState.data, Toast.LENGTH_LONG).show()
//                    val action = ClassificationFragmentDirections.actionClassificationToResult(uiState.data)
//                    findNavController().navigate(action)
                }
                is UIState.Failure -> {
                    Toast.makeText(requireContext(), uiState.message, Toast.LENGTH_SHORT).show()}
            }
        })
    }

    private fun onClassification(imageBitmap: Bitmap?) {
        classificationViewModel.doClassification(imageBitmap!!)
    }

    private fun setupFab() {
        // Set the Extended floating action button to shrinked state initially
        binding.extendedTakePic.shrink()
        binding.extendedTakePic.setOnClickListener {  uiViewModel.checkFab()}
        binding.galeriFab.setOnClickListener { imageFromGaleri.launch("image/*") }
    }


    private fun hideView() {
        binding.cameraFab.hide()
        binding.galeriFab.hide()
        binding.extendedTakePic.shrink()
    }

    private fun showView() {
        binding.cameraFab.show()
        binding.galeriFab.show()
        binding.extendedTakePic.extend()
    }
}
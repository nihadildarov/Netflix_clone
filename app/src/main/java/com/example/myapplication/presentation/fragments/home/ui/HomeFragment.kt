package com.example.myapplication.presentation.fragments.home.ui

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.util.Constants.IMAGE_URL
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.domain.remote.Mapper.toMovieResult
import com.example.myapplication.domain.remote.models.MovieResult
import com.example.myapplication.presentation.fragments.home.adapters.MovieAdapterRecyclersBig
import com.example.myapplication.presentation.fragments.home.adapters.MovieAdapterRecyclersContinueWatching
import com.example.myapplication.presentation.fragments.home.adapters.MovieAdapterRecyclersMedium
import com.example.myapplication.presentation.adapter_listener.MovieClickListener
import com.example.myapplication.presentation.fragments.home.adapters.AdapterHomeRecyclerCollection
import com.example.myapplication.presentation.fragments.home.model.ChildRcyModel
import com.example.myapplication.presentation.fragments.home.viewmodel.HomeViewModel
import com.example.myapplication.util.Resource
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBigPoster()
        searchBtnClick()
        btnProfileClick()
        cardViewPosterClick()
        setRecyclers()
    }


    private fun setBigPoster() {

        viewModel.popularMovieList.observe(viewLifecycleOwner) {
            when (it) {
                Resource.Loading -> {
                    binding.progressBar.visibility = VISIBLE
                    binding.imgPosterHeader.visibility = GONE
                    binding.btnPlayHeader.visibility = GONE
                    binding.btnAddMyListHeader.visibility = GONE
                }

                is Resource.Success -> {
                    binding.progressBar.visibility = GONE
                    binding.imgPosterHeader.visibility = VISIBLE
                    binding.btnPlayHeader.visibility = VISIBLE
                    binding.btnAddMyListHeader.visibility = VISIBLE

                    val movie = it.data.random()
                    val url = "$IMAGE_URL${movie.poster_path}"

                    loadBitmapFromUrl(url, onSuccess = { dominantColors ->
                        val color1 = dominantColors.firstOrNull() ?: Color.WHITE
                        val color2 = Color.BLACK
                        Log.i("setBigPoster", color1.toString())
                        val gradientDrawable = GradientDrawable(
                            GradientDrawable.Orientation.TOP_BOTTOM,
                            intArrayOf(color1, color2)
                        )
                        binding.constraintHome.background = gradientDrawable
                    }, onError = {
                        Log.e("setBigPoster", "Error")
                    }
                    )

                    Log.i("setBigPoster", "Done")
                    Picasso.get().load(url).into(binding.imgPosterHeader)
                    binding.imgPosterHeader.setOnClickListener {
                        Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show()
                        findNavController().navigate(
                            HomeFragmentDirections.actionHomeToMovieDetails(
                                movie.id.toLong()
                            )
                        )

                    }
                    btnPlayClickBigPoster(movie.id.toLong())

                }

                is Resource.Error -> {
                    Toast.makeText(context, "Error Occurred", Toast.LENGTH_LONG).show()
                    Log.e("upComingMovieListObserve", "Error occurred: ${it.exception}")
                }
            }
        }
    }


    private fun loadBitmapFromUrl(
        url: String,
        onSuccess: (List<Int>) -> Unit,
        onError: () -> Unit
    ) {
        Picasso.get().load(url).into(object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {

                Log.i("setBigPoster", bitmap.hashCode().toString())
                if (bitmap != null) {
                    Log.i("setBigPoster", "onPrepareLoaded")
                    // Extract dominant colors from the bitmap
                    Palette.from(bitmap).generate { palette ->
                        val dominantColors = mutableListOf<Int>()
                        palette?.dominantSwatch?.let {
                            dominantColors.add(it.rgb)
                        }
                        onSuccess(dominantColors)
                    }
                } else {
                    onError()
                }
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                Log.i("setBigPoster", e.toString())
                onError()
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                Log.i("setBigPoster", "onPrepareLoad")
            }
        })
    }


    private fun setViewModelData(
        adapterParent: AdapterHomeRecyclerCollection
    ) {
        viewModel.popularMovieList.observe(viewLifecycleOwner) {
            when (it) {
                Resource.Loading -> {
                    adapterParent.showProgress()
                }

                is Resource.Success -> {
                    adapterParent.hideProgress()
                    adapterParent.submitList(
                        listOf(
                            ChildRcyModel(
                                "Text1",
                                it.data.toMovieResult()
                            )
                        )
                    )
                }

                is Resource.Error -> {
                    adapterParent.showProgress()
                    Toast.makeText(context, "Error Occurred", Toast.LENGTH_LONG).show()
                    Log.e("popularMovieListObserve", "Error occurred: ${it.exception}")
                }
            }

        }


        viewModel.upComingMovieList.observe(viewLifecycleOwner) {
            when (it) {
                Resource.Loading -> {
                }

                is Resource.Success -> {

                }

                is Resource.Error -> {
                    Toast.makeText(context, "Error Occurred", Toast.LENGTH_LONG).show()
                    Log.e("upComingMovieListObserve", "Error occurred: ${it.exception}")
                }
            }
        }
    }


    private fun setTopRatedMovieFromViewModel(
        adapterParent: AdapterHomeRecyclerCollection
    ) {


        viewModel.topRatedMovieList.observe(viewLifecycleOwner) {
            when (it) {
                Resource.Loading -> {
                    adapterParent.showProgress()
                }

                is Resource.Success -> {


                    adapterParent.hideProgress()


                    adapterParent.submitList(
                        listOf(
                            ChildRcyModel(getTextList()[0],it.data.toMovieResult().asReversed()),
                            ChildRcyModel(getTextList()[1],it.data.toMovieResult()),
                            ChildRcyModel(getTextList()[2],it.data.toMovieResult()),
                            ChildRcyModel(getTextList()[3],it.data.toMovieResult()),
                            ChildRcyModel(getTextList()[4],it.data.toMovieResult()),
                            ChildRcyModel(getTextList()[5],it.data.toMovieResult()),
                            ChildRcyModel(getTextList()[6],it.data.toMovieResult()),
                            ChildRcyModel(getTextList()[7],it.data.toMovieResult()),
                            ChildRcyModel(getTextList()[8],it.data.toMovieResult()),
                            ChildRcyModel(getTextList()[9],it.data.toMovieResult()),
                            ChildRcyModel(getTextList()[10],it.data.toMovieResult()),
                            ChildRcyModel(getTextList()[11],it.data.toMovieResult()),
                            ChildRcyModel(getTextList()[12],it.data.toMovieResult()),
                            ChildRcyModel(getTextList()[13],it.data.toMovieResult()),
                            ChildRcyModel(getTextList()[14],it.data.toMovieResult()),
                            ChildRcyModel(getTextList()[15],it.data.toMovieResult())
                        )
                    )

                }

                is Resource.Error -> {
                    adapterParent.showProgress()
                    Toast.makeText(context, "Error Occurred", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    private fun cardViewPosterClick() {
        binding.cardViewPoster.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_movieDetails)
        }
    }


    private fun searchBtnClick() {
        binding.imgSearch.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeToSearch())
        }
    }

    private fun btnPlayClickBigPoster(movieId: Long) {
        binding.btnPlayHeader.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToFullScreenFragment(
                    movieId
                )
            )
        }
    }

    private fun btnProfileClick() {
        binding.imgAvatar.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProfilesAndMoreFragment())
        }
    }


    private fun setRecyclers() {
        val adapterMedium = MovieAdapterRecyclersMedium(object : MovieClickListener {
            override fun movieClickListener(movieId: Long) {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeToMovieDetails(movieId)
                )
            }
        })

        val adapterBig = MovieAdapterRecyclersBig(object : MovieClickListener {
            override fun movieClickListener(movieId: Long) {
                findNavController().navigate(HomeFragmentDirections.actionHomeToMovieDetails(movieId))
            }
        })

        val adapterContinueWatching = MovieAdapterRecyclersContinueWatching(object :
            MovieClickListener {
            override fun movieClickListener(movieId: Long) {
                findNavController().navigate(HomeFragmentDirections.actionHomeToMovieDetails(movieId))
            }
        })

        binding.rcyHomeRecyclersCollection.layoutManager = LinearLayoutManager(requireContext())
        val adapterParent =
            AdapterHomeRecyclerCollection(adapterBig, adapterMedium, adapterContinueWatching)
        setTopRatedMovieFromViewModel(adapterParent)
        binding.rcyHomeRecyclersCollection.adapter = adapterParent

    }

    private fun submitListParentAdapter(adapterParent: AdapterHomeRecyclerCollection,movieResult:List<MovieResult>){
        val textList = getTextList()
        for (i in textList){
            adapterParent.submitList(listOf( ChildRcyModel(i,movieResult)))
        }
    }


    private fun getTextList(): List<String> {
        return listOf(
            "Soapy TV Dramas",
            "Bingeworthy TV Shows",
            "Critically-acclaimed Dark US TV Shows",
            "Only on Netflix",
            "Continue watching",
            "Action & Adventure Movies",
            "Made in Turkey",
            "New Releases",
            "Crime TV Shows",
            "Kids & Family Movies",
            "Binge-worthy US TV Shows",
            "Turkish TV Shows",
            "My List",
            "Top Picks for Nihad",
            "TV Shows",
            "Hollywood Family Movies",
            "Bingeworthy Suspenseful TV Shows",
            "Watch Together fo Older Kids",
            "Because you watched Midnight at the Pera Palace",
            "Comedies",
            "Emotional European TV Dramas",
            "Movies Written By Women",
            "Workplace TV Dramas",
            "We Think You'll Love These",
            "Family Comedy Movies",
            "Award-Winning US TV Shows",
            "Watch In One Weekend",
            "Critically Acclaimed TV Shows",
            "Award-Winning TV Shows",
            "Blockbuster Movies",
            "International TV Dranas",
            "Young Adult Movies & Shows",
            "Familiar TV Favorites",
            "Top Series",
            "Comedy Movies",
            "Because you watched Love Tactics",
            "Watch In One Night"
        )
    }


    private fun setProgressVisibilityForParentAdapter(adapterParent: AdapterHomeRecyclerCollection,isVisible: Boolean){
        if (isVisible){
            adapterParent.showProgress()
        }else{
            adapterParent.hideProgress()
        }
    }
}



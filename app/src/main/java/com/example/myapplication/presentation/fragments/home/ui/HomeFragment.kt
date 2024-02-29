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
import com.example.myapplication.presentation.fragments.home.adapters.MovieAdapterRecyclerDownloads
import com.example.myapplication.presentation.fragments.home.adapters.MovieAdapterRecyclersBig
import com.example.myapplication.presentation.fragments.home.adapters.MovieAdapterRecyclersContinueWatching
import com.example.myapplication.presentation.fragments.home.adapters.MovieAdapterRecyclersMedium
import com.example.myapplication.presentation.fragments.home.adapters.GamesAdapterRecyclersGames
import com.example.myapplication.presentation.adapter_listener.MovieClickListener
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
        createRecyclers()
        searchBtnClick()

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
                        findNavController().navigate(HomeFragmentDirections.actionHomeToMovieDetails(movie.id.toLong()))

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
        adapterBig: MovieAdapterRecyclersBig,
        adapterContinueWatching: MovieAdapterRecyclersContinueWatching
    ) {
        viewModel.popularMovieList.observe(viewLifecycleOwner) {
            when (it) {
                Resource.Loading -> {
                    adapterBig.showProgress()
                }

                is Resource.Success -> {
                    adapterBig.hideProgress()
                    adapterBig.submitList(it.data)
                }

                is Resource.Error -> {
                    Toast.makeText(context, "Error Occurred", Toast.LENGTH_LONG).show()
                    Log.e("popularMovieListObserve", "Error occurred: ${it.exception}")
                }

            }

        }


        viewModel.upComingMovieList.observe(viewLifecycleOwner) {
            when (it) {
                Resource.Loading -> {
                    //Set progressbar visible
                }

                is Resource.Success -> {
                    adapterContinueWatching.submitList(it.data)
                }

                is Resource.Error -> {
                    Toast.makeText(context, "Error Occurred", Toast.LENGTH_LONG).show()
                    Log.e("upComingMovieListObserve", "Error occurred: ${it.exception}")
                }
            }
        }
    }


    private fun setTopRatedMovieFromViewModel(
        adapterMedium: MovieAdapterRecyclersMedium
    ) {
        viewModel.topRatedMovieList.observe(viewLifecycleOwner) {
            when (it) {
                Resource.Loading -> {
                    adapterMedium.showProgress()
                }

                is Resource.Success -> {
                    adapterMedium.hideProgress()
                    adapterMedium.submitList(it.data.shuffled())
                }

                is Resource.Error -> {
                    adapterMedium.showProgress()
                    Toast.makeText(context, "Error Occurred", Toast.LENGTH_LONG).show()
                    Log.e("topRatedMovieListObserve", "Error occurred: ${it.exception}")
                }
            }
        }
    }


    private fun createRecyclers() {


        val textList = listOf<Any>(
            //"Mobile Games",
            //"Downloads For You",
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

        setViewModelData(adapterBig, adapterContinueWatching)



        binding.cardViewPoster.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_movieDetails)
        }


        var prevId = 1



        for (i in 1..textList.size) {
            val adapterMedium = MovieAdapterRecyclersMedium(object : MovieClickListener {
                override fun movieClickListener(movieId: Long) {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeToMovieDetails(
                            movieId
                        )
                    )
                }

            })

            setTopRatedMovieFromViewModel(adapterMedium)

            val constraintLayout = binding.constraintHome
            val constraintSet = ConstraintSet()


            val textHeader = TextView(requireContext())
            textHeader.text = textList[i - 1].toString()
            textHeader.id = View.generateViewId()
            textHeader.setTextColor(resources.getColor(R.color.white))
            val paramsTxt = ConstraintLayout.LayoutParams(
                0,
                -2
            )
            textHeader.layoutParams = paramsTxt
            textHeader.setTypeface(null, Typeface.BOLD)
            textHeader.textSize = 18f


            val recycler = RecyclerView(requireContext())
            recycler.id = View.generateViewId()
            recycler.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            val paramsRcy = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            recycler.layoutParams = paramsRcy

            constraintLayout.addView(textHeader)
            constraintLayout.addView(recycler)

            constraintSet.clone(constraintLayout)


            //TextHeaders constraints
            //Top
            if (i == 1) {
                constraintSet.connect(
                    textHeader.id,
                    ConstraintSet.TOP,
                    binding.cardViewPoster.id,
                    ConstraintSet.BOTTOM,
                    50
                )
            } else {
                constraintSet.connect(
                    textHeader.id,
                    ConstraintSet.TOP,
                    prevId,
                    ConstraintSet.BOTTOM,
                    50
                )
            }

            //Start
            constraintSet.connect(
                textHeader.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START,
                30
            )
            //End
            if (textList[i - 1] == "Mobile Games" || textList[i - 1] == "My List") {
                val txtMyList = TextView(requireContext())
                when (textList[i - 1]) {
                    "Mobile Games" -> txtMyList.text = "My List"
                    "My List" -> txtMyList.text = "See All"
                }

                txtMyList.id = View.generateViewId()
                txtMyList.setTextColor(resources.getColor(R.color.white))
                val paramsTxtMyList = ConstraintLayout.LayoutParams(
                    -2,
                    -2
                )
                txtMyList.layoutParams = paramsTxtMyList
                txtMyList.setTypeface(null, Typeface.BOLD)
                txtMyList.textSize = 18f
                txtMyList.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                txtMyList.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null, null,
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_chevron_right), null
                )
                constraintLayout.addView(txtMyList)

                //START
                constraintSet.connect(
                    txtMyList.id,
                    ConstraintSet.START,
                    textHeader.id,
                    ConstraintSet.END
                )

                //TOP
                constraintSet.connect(
                    txtMyList.id,
                    ConstraintSet.TOP,
                    textHeader.id,
                    ConstraintSet.TOP
                )
                //END
                constraintSet.connect(
                    txtMyList.id,
                    ConstraintSet.END,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.END
                )
                //BOTTOM
                constraintSet.connect(
                    txtMyList.id,
                    ConstraintSet.BOTTOM,
                    textHeader.id,
                    ConstraintSet.BOTTOM
                )

                constraintSet.connect(
                    textHeader.id,
                    ConstraintSet.END,
                    txtMyList.id,
                    ConstraintSet.START
                )
            } else {
                constraintSet.connect(
                    textHeader.id,
                    ConstraintSet.END,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.END
                )
            }


            //Recyclers constraints
            //Top
            constraintSet.connect(
                recycler.id,
                ConstraintSet.TOP,
                textHeader.id,
                ConstraintSet.BOTTOM,
                8
            )
            //Start
            constraintSet.connect(
                recycler.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START,
                10
            )
            //End
            constraintSet.connect(
                recycler.id,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END
            )



            prevId = recycler.id

            //Setting recyclers adapters
            when {
                textList[i - 1] == "Only on Netflix" -> recycler.adapter = adapterBig
                textList[i - 1] == "Continue watching" -> recycler.adapter = adapterContinueWatching
                else -> recycler.adapter = adapterMedium

            }

            constraintSet.applyTo(constraintLayout)
        }
    }


    private fun searchBtnClick() {
        binding.imgSearch.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeToSearch())
        }
    }

    private fun btnPlayClickBigPoster(movieId:Long){
        binding.btnPlayHeader.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFullScreenFragment(movieId))
        }
    }

}



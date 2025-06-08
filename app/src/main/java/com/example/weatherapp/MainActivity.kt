package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.airbnb.lottie.LottieAnimationView
import com.example.weatherapp.MainActivity
import com.example.weatherapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


//  02dac1a293be66865e85d4d84e875730
//{"coord":{"lon":77.2167,"lat":28.6667},"weather":[{"id":802,"main":"Clouds","description":"scattered clouds","icon":"03d"}],"base":"stations","main":{"temp":312.12,"feels_like":311.64,"temp_min":312.12,"temp_max":312.12,"pressure":995,"humidity":22,"sea_level":995,"grnd_level":971},"visibility":10000,"wind":{"speed":4.43,"deg":145,"gust":5.15},"clouds":{"all":50},"dt":1748692347,"sys":{"country":"IN","sunrise":1748649238,"sunset":1748699039},"timezone":19800,"id":1273294,"name":"Delhi","cod":200}
class MainActivity : AppCompatActivity() {

    val key = "02dac1a293be66865e85d4d84e875730"
    private val binding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var searchview=binding.searchView
    //Default City
    fetchWeatherData("Delhi")

    // Handle SearchView
    searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            if(query != null){
                fetchWeatherData(query)
            }
            return true
        }

        override fun onQueryTextChange(newText: String?)=false

    })
}

    private fun fetchWeatherData(city:String){
        val retrofitbuilder = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Apinterface::class.java)

        val retrofitdata = retrofitbuilder.GetProductData("$city", key)

        retrofitdata.enqueue(object : retrofit2.Callback<WeatherApp> {
            override fun onResponse(call: Call<WeatherApp?>, response: Response<WeatherApp?>) {
                val Weatherdata = response.body()
                if (Weatherdata != null) {
                    val temp = (Weatherdata.main.temp) - 273.15
                    val humidity = Weatherdata.main.humidity
                    val wind = Weatherdata.wind.speed
                    val pressure = Weatherdata.main.pressure
                    val city = Weatherdata.name
                    val sunset = Weatherdata.sys.sunset
                    val sunrise = Weatherdata.sys.sunrise
                    val weather = Weatherdata.weather[0].main
                    val min = (Weatherdata.main.temp_min) - 273.15
                    val max = (Weatherdata.main.temp_max) - 273.15
                    val weathercondition = Weatherdata.weather[0].description

                    //To set the Night Background
                    val currenttime= SimpleDateFormat("hh:mm .a", Locale.getDefault()).format(Date())
                    val nighttime= formatTimestampToTime(sunset)

                    binding.cityname.text = city
                    binding.temperature.text = String.format("%.0f °C", temp)
                    binding.weather.text = weathercondition
                    binding.maxtemp.text = String.format("Max Temperature: %.0f °C", max)
                    binding.mintemp.text = String.format("Min Temperature: %.0f °C", min)
                    binding.humidity.text = humidity.toString() + " %"
                    binding.windspeed.text = wind.toString() + "m/s"
                    binding.weathercondition.text = weather
                    binding.sealevel.text = pressure.toString() + "hPA"
                    binding.sunrise.text = formatTimestampToTime(sunrise)
                    binding.sunset.text = formatTimestampToTime(sunset)
                    binding.day.text = SimpleDateFormat("EEEE", Locale.getDefault()).format(Date())
                    binding.date.text = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(Date())
                    binding.ctime.text=currenttime



                   if(isDay(sunrise ,sunset)){
                       when {
                           weathercondition.contains("clear") -> {
                               binding.main.setBackgroundResource(R.drawable.sunny_background)
                           }

                           weathercondition.contains("clouds") -> {
                               binding.main.setBackgroundResource(R.drawable.colud_background)
                               binding.lottieAnimationView.setAnimation(R.raw.cloud)
                               binding.lottieAnimationView.playAnimation()
                               binding.lottieAnimationView.loop(true)
                           }

                           weathercondition.contains("rain") -> {
                               binding.main.setBackgroundResource(R.drawable.rain_background)
                               binding.lottieAnimationView.setAnimation(R.raw.rain)
                               binding.lottieAnimationView.playAnimation()
                               binding.lottieAnimationView.loop(true)

                           }

                           weathercondition.contains("thunderstrom") -> {
                               binding.main.setBackgroundResource(R.drawable.colud_background)
                               binding.lottieAnimationView.setAnimation(R.raw.cloud)
                               binding.lottieAnimationView.playAnimation()
                               binding.lottieAnimationView.loop(true)
                           }

                           weathercondition.contains("rain") -> {
                               binding.main.setBackgroundResource(R.drawable.snow_background)
                               binding.lottieAnimationView.setAnimation(R.raw.snow)
                               binding.lottieAnimationView.playAnimation()
                               binding.lottieAnimationView.loop(true)
                           }
                       }
                   }else{
                       binding.main.setBackgroundResource(R.drawable.rain_background)
                       binding.lottieAnimationView.setAnimation(R.raw.moon)
                       binding.lottieAnimationView.playAnimation()
                       binding.lottieAnimationView.loop(true)
                       binding.temperature.setTextColor(resources.getColor(R.color.white))
                       binding.weather.setTextColor(resources.getColor(R.color.white))
                       binding.maxtemp.setTextColor(resources.getColor(R.color.white))
                       binding.cityname.setTextColor(resources.getColor(R.color.white))
                       binding.mintemp.setTextColor(resources.getColor(R.color.white))
                       binding.humidity.setTextColor(resources.getColor(R.color.white))
                       binding.windspeed.setTextColor(resources.getColor(R.color.white))
                       binding.weathercondition.setTextColor(resources.getColor(R.color.white))
                       binding.sealevel.setTextColor(resources.getColor(R.color.white))
                       binding.sunrise.setTextColor(resources.getColor(R.color.white))
                       binding.sunset.setTextColor(resources.getColor(R.color.white))
                       binding.day.setTextColor(resources.getColor(R.color.white))
                       binding.date.setTextColor(resources.getColor(R.color.white))
                       binding.ctime.setTextColor(resources.getColor(R.color.white))
                       binding.textView4.setTextColor(resources.getColor(R.color.white))

                       when{
                           weathercondition.contains("clear") -> {
                               binding.main.setBackgroundResource(R.drawable.nightbackground)
                           }weathercondition.contains("clouds") -> {
                               binding.main.setBackgroundResource(R.drawable.colud_background)
                               binding.lottieAnimationView.setAnimation(R.raw.moon)
                               binding.lottieAnimationView.playAnimation()
                               binding.lottieAnimationView.loop(true)
                       }weathercondition.contains("rain") -> {
                               binding.main.setBackgroundResource(R.drawable.rain_background)
                               binding.lottieAnimationView.setAnimation(R.raw.moon)
                               binding.lottieAnimationView.playAnimation()
                               binding.lottieAnimationView.loop(true)

                       }
                       }

                   }
                }
            }

            override fun onFailure(call: Call<WeatherApp?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
                Toast.makeText(this@MainActivity, "No Data Found", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun isDay(sunrise: Int, sunset: Int): Boolean {
        val currenttimeMillis=System.currentTimeMillis()
        val sunrisetime=sunrise*1000L
        val sunsettime=sunset*1000L

        return currenttimeMillis>sunrisetime && currenttimeMillis<sunsettime
    }
    }


private fun formatTimestampToTime(timestamp: Int): String {
    val date = Date(timestamp * 1000L)
    val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return sdf.format(date)
}
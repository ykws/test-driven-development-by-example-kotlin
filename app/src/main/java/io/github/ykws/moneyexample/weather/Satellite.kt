package io.github.ykws.moneyexample.weather

class Satellite {
  fun getWeather(latitude: Double, longitude: Double): Weather {
    return Weather.SUNNY
  }
}
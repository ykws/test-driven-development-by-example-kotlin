package io.github.ykws.moneyexample.weather

import org.junit.After
import org.junit.Before

import org.assertj.core.api.Assertions.*
import com.nhaarman.mockitokotlin2.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Spy

class WeatherForecastTest {
  @Mock
  lateinit var satellite: Satellite
  @Mock
  lateinit var recorder: WeatherRecorder
  @Spy
  lateinit var formatter: WeatherFormatter

  lateinit var weatherForecast: WeatherForecast

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)

    satellite = mock(name = "MockSatellite") {
      on { getWeather(any(), any()) } doReturn Weather.CLOUDY
      on { getWeather(eq(37.580006), eq(-122.345106)) } doReturn Weather.SUNNY
      on { getWeather(eq(37.792872), eq(-122.396915)) } doReturn Weather.RAINY
    }

    recorder = mock(name = "MockRecorder")

    formatter = spy(WeatherFormatter())
    weatherForecast = WeatherForecast(satellite, recorder, formatter)
  }

  @After
  fun tearDown() {
  }

  @Test
  fun shouldBringUmbrella_givenInJapan_returnsFalse() {
    val actual = weatherForecast.shouldBringUmbrella(35.669784, 139.817728)
    assertThat(actual).isFalse()
  }

  @Test
  fun shouldBringUmbrella_givenBurlingame_returnTrue() {
    val actual = weatherForecast.shouldBringUmbrella(37.792872, -122.396915)
    assertThat(actual).isTrue()
  }

  @Test
  fun recordCurrentWeather_assertRecorderCalled() {
    weatherForecast.recordCurrentWeather(37.580006, -122.345106)

    argumentCaptor<Record>().apply {
      verify(recorder, times(1)).record(capture())
      assertThat(firstValue.description).isEqualTo("Weather is SUNNY")
    }
  }

  @Test
  fun recordCurrentWeather_assertFormatterCalled() {
    weatherForecast.recordCurrentWeather(37.580006, -122.345106)
    verify(formatter, times(1)).format(any())
  }
}
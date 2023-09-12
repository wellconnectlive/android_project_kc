package live.wellconnect.wellconnect.presentation.location

import android.annotation.SuppressLint

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


@SuppressLint("MissingPermission")
@Composable
fun MyLocationScreen(uiID: String) {
    val puebla = LatLng(19.03793, -98.20346)
    val mexico = LatLng(19.3529, -99.12766)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(puebla, 10f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = puebla),
            title = "Puebla",
            snippet = "Marker en Puebla"
        )

        Marker(
            state = MarkerState(position = mexico),
            title = "Singapore",
            snippet = "Marker en mexico"
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MyLocationScreen_Preview(){
    MyLocationScreen("AAAEEE11111")
}
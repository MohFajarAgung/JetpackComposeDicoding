package com.dicoding.listmusik.ui.screen.detail


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.dicoding.listmusik.R
import com.dicoding.listmusik.ViewModelFactory
import com.dicoding.listmusik.di.Injection
import com.dicoding.listmusik.ui.common.UiState

@Composable
fun DetailMusikScreen(
    musikId: Long,
    viewModel: DetailMusikViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
    navigateToCart: () -> Unit,
){
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getMusikById(musikId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.musik.photoUrl,
                    data.musik.judul,
                    data.musik.desc,
                    count = data.count,
                    onAddToFavorite = { count ->
                        viewModel.addToFavorite(data.musik, count)
                        navigateToCart()
                    }
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    image: String,
    title: String,
    desc:  String,
    count: Int,
    onAddToFavorite: (count: Int) -> Unit,
) {
    val orderCount by rememberSaveable { mutableStateOf(count) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(60.dp)

    ) {
        AsyncImage(
            model = image, contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .size(200.dp)
                .clip(RoundedCornerShape(16.dp))

        )
        Text(
            text = title,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(5.dp),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,

                ),
        )
        Text(
            text = desc,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(5.dp),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 15.sp,
            ),
        )
        if(orderCount > 0){
            DeleteFavoriteButton(
                text = stringResource(id = R.string.delete_favorite),
                onClick = {
                    onAddToFavorite(orderCount - 1)
                }
            )
        }
        else{
        FavoriteButton(
            text = stringResource(R.string.add_to_favorite),
            onClick = {
                onAddToFavorite(orderCount + 1)
            }
        )
        }
    }
}

@Composable
fun FavoriteButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .semantics(mergeDescendants = true) {
                contentDescription = "Favorite Button"
            }
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}
@Composable
fun DeleteFavoriteButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .semantics(mergeDescendants = true) {
                contentDescription = "Delete Favorite Button"
            }
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}
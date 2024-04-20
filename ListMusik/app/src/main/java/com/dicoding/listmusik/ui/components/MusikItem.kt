package com.dicoding.listmusik.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dicoding.listmusik.R

@Composable
fun MusikListItem(
    judul: String,
    photoUrl: String,
    navigateToDetail: (Long) -> Unit,
    musikId: Long,
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =  Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.primary)
            .clickable {
                navigateToDetail(musikId)
            }

    ){
        AsyncImage(model = photoUrl, contentDescription =null,
            contentScale =  ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .height(160.dp)
                .width(120.dp)
                .clip(RoundedCornerShape(16.dp))

        )
        Text(
            text = judul,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 10.dp, end = 10.dp),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White
            ),
        )
    }
}

@Composable
fun FavoriteMusikListItem(
    judul: String,
    photoUrl: String,
    navigateToDetail: (Long) -> Unit,
    musikId: Long,
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =  Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.primary)
            .clickable {
                navigateToDetail(musikId)
            }

    ){
        AsyncImage(model = photoUrl, contentDescription =null,
            contentScale =  ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .height(160.dp)
                .width(120.dp)
                .clip(RoundedCornerShape(16.dp))

        )
        Text(
            text = judul,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 10.dp, end = 10.dp),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White
            ),
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchMusikBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {},
        active = false,
        onActiveChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        placeholder = {
            Text(stringResource(R.string.search_musik))
        },
        shape = MaterialTheme.shapes.large,
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp)
    ) {
    }
}
package com.dicoding.listmusik.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.listmusik.MusikRepository

import com.dicoding.listmusik.ViewModelFactory
import com.dicoding.listmusik.ui.components.MusikListItem
import com.dicoding.listmusik.ui.components.SearchMusikBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(MusikRepository())),
    navigateToDetail: (Long) -> Unit,
){
    val groupedMusik by viewModel.groupedMusik.collectAsState()
    val query by viewModel.query

    Column(modifier = modifier){
        val listState = rememberLazyListState()
        SearchMusikBar(query = query, onQueryChange = viewModel::search ,
            modifier = Modifier.background(MaterialTheme.colorScheme.primary))
        LazyColumn(modifier = Modifier.padding(top = 10.dp).testTag("MusikList"), state = listState){
            groupedMusik.forEach{ ( init,data) ->
                items(data, key = {it.id}) { dataMusik ->
                    MusikListItem(judul = dataMusik.judul, photoUrl = dataMusik.photoUrl,
                        navigateToDetail = navigateToDetail, musikId = dataMusik.id
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

        }

    }
}









package com.example.instantsystemtechnicaltest.newslist


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.instantsystemtechnicaltest.data.model.Article

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsList(newsList :List<Article?>, onClick: (Article) -> Unit) {

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(items = newsList) { news ->
            news?.let {
                Card(
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                        .clickable {

                            onClick(it)
                        },
                    shape = RoundedCornerShape(corner = CornerSize(16.dp)),

                    ) {
                    Column {
                        Text(
                            text = it.title.toString(),
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(15.dp)
                        )
                        Log.e("TestURL", it.urlToImage.toString())
                        GlideImage(
                            model = it.urlToImage,
                            contentDescription = "news picture",
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            it.error("https://cdn.pixabay.com/photo/2016/02/01/00/56/news-1172463_1280.jpg")
                        }
                    }
                }

            }
        }

    }
}



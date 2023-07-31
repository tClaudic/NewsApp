package com.example.instantsystemtechnicaltest.newsdetail


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.instantsystemtechnicaltest.R
import com.example.instantsystemtechnicaltest.data.model.Article


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ArticleScreen(article: Article?, onNewsLinkButtonClicked: (String) -> Unit) {

    if (article != null) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())

        ) {


            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                article.title?.let {
                    Text(
                        text = it, style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                GlideImage(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    model = article.urlToImage,
                    contentDescription = "article picture"
                ) {
                    it.error("https://cdn.pixabay.com/photo/2016/02/01/00/56/news-1172463_1280.jpg")
                }
                article.content?.let {
                    Text(
                        modifier = Modifier.padding(top = 20.dp),
                        text = it,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                article.author?.let {
                    Text(
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(top = 10.dp),
                        text = it
                    )
                }
            }



            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(
                    modifier = Modifier
                        .padding(16.dp),
                    contentPadding = PaddingValues(
                        start = 20.dp,
                        top = 12.dp,
                        end = 20.dp,
                        bottom = 12.dp
                    ),


                    onClick = { article.url?.let { onNewsLinkButtonClicked(it) } }) {
                    Icon(
                        modifier = Modifier.size(ButtonDefaults.IconSize),
                        painter = painterResource(id = R.drawable.ic_launch_web_view),
                        contentDescription = "Launch Button icon"
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(text = stringResource(id = R.string.news_button_link))

                }

            }
        }

    } else {
        Text(text = "Loading")
    }

}

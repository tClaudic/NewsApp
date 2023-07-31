package com.example.instantsystemtechnicaltest

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.instantsystemtechnicaltest.newsdetail.ArticleScreen
import com.example.instantsystemtechnicaltest.newslist.NewsList
import com.example.instantsystemtechnicaltest.newslist.NewsListViewModel


@Composable
fun NewsAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    currentScreen: NewsAppScreen
) {
    TopAppBar(
        title = { Text(stringResource(id = currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back button"
                    )
                }
            }
        }
    )
}


@Composable
fun NewsApp(
    modifier: Modifier = Modifier,
) {
    val viewModel = hiltViewModel<NewsListViewModel>()
    val navHostController: NavHostController = rememberNavController()
    val backStackEntry by navHostController.currentBackStackEntryAsState()
    val currentScreen = NewsAppScreen.valueOf(backStackEntry?.destination?.route ?: NewsAppScreen.NewsListScreen.name)

    Scaffold(
        topBar = {
            NewsAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navHostController.previousBackStackEntry != null,
                navigateUp = { navHostController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val newsList = viewModel.newsList.collectAsState()
        val selectedArticle = viewModel.selectedArticle.collectAsState()

        NavHost(
            navController = navHostController,
            startDestination = NewsAppScreen.NewsListScreen.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(NewsAppScreen.NewsListScreen.name) {
                NewsList(newsList.value,onClick = {
                    viewModel.setSelectedArticle(it)
                    navHostController.navigate(NewsAppScreen.NewsDetailScreen.name) })
            }
            composable(NewsAppScreen.NewsDetailScreen.name) {
                val context = LocalContext.current
                ArticleScreen(selectedArticle.value, onNewsLinkButtonClicked = {
                    newsWebIntent(context = context, url = it)
                })
            }
        }
    }

}


fun newsWebIntent(context: Context, url : String){

    val webIntent: Intent = Uri.parse(url).let {
        Intent(Intent.ACTION_VIEW,it)
    }
    context.startActivity(webIntent)

}

enum class NewsAppScreen(@StringRes val title: Int) {
    NewsListScreen(title = R.string.news_list),
    NewsDetailScreen(title = R.string.news_detail)
}
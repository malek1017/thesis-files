package com.example.groceryapp.activity

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.groceryapp.app.MyApplication
import com.example.groceryapp.database.EventDao
import com.example.groceryapp.model.Event
import com.example.groceryapp.model.Order
import com.google.android.material.composethemeadapter.MdcTheme
import java.time.Instant
import kotlin.random.Random

class EventsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val eventsDao = (applicationContext as MyApplication).appContainer.eventDao
        setContent {
            MdcTheme {
                EventsScreen(eventsDao)
            }
        }
    }
}

fun List<Event>.toCsvLikeString(): String {
    val eventsList = this
    return buildString {
        appendLine("Start of CSV:")
        appendLine("ID,EventName,ExtraInformation,CreatedAt")
        eventsList.forEachIndexed { index, event ->
            val extraInformationWithoutCommas = event.extraInformation?.replace(",", " ")
            append("${event.id},${event.eventName},$extraInformationWithoutCommas,${event.createdAt}")
            if (index != eventsList.lastIndex) {
                appendLine()
            }
        }
    }
}

@Composable
fun EventsScreen(eventsDao: EventDao) {
    val events by eventsDao.getAllEventsFlow().collectAsState(emptyList())
    Column {
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = { Log.d("EventActivity", events.toCsvLikeString()) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text("Print database events in the logs like a CSV")
        }
        Spacer(Modifier.height(8.dp))
        LazyColumn {
            items(
                items = events,
                key = { event -> event.id }
            ) { event ->
                EventCard(
                    event = event,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                )
            }
        }
    }
}

@Composable
fun EventCard(
    event: Event,
    modifier: Modifier = Modifier,
) {
    Card(
        elevation = 4.dp,
        modifier = modifier.border(2.dp, MaterialTheme.colors.primary, MaterialTheme.shapes.medium),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
        ) {
            Text(
                text = "${event.eventName} (event #${event.id})",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.fillMaxWidth(),
            )
            Text("Created at: ${event.createdAt}", style = MaterialTheme.typography.body1)
            if (event.extraInformation != null) {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        "Extra information: ${event.extraInformation}",
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun EventCardPreview(
    @PreviewParameter(EventPreviewProvider::class) event: Event,
) {
    MdcTheme {
        Surface(color = MaterialTheme.colors.background) {
            EventCard(event)
        }
    }
}

class EventPreviewProvider : CollectionPreviewParameterProvider<Event>(
    listOf(
        Event(10, "app_opened", null, Instant.now().minusSeconds(160)),
        Event(Random.nextLong(), "button_click", null, Instant.now().minusSeconds(160)),
        Event(Random.nextLong(), "button_click", null, Instant.now().minusSeconds(160)),
        Event(Random.nextLong(), "something", null, Instant.now().minusSeconds(160)),
        Event(
            Random.nextLong(),
            "test",
            Order(Random.nextLong(), Random.nextInt()).toString(),
            Instant.now().minusSeconds(160)
        ),
    )
)

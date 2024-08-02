package xd.arkosammy.cocoon.screen

import xd.arkosammy.cocoon.driver.ContentSource

interface ContentBackedView : View {

    val contentSource: ContentSource

    fun save()

    fun load()

}
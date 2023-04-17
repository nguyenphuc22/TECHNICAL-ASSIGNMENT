package com.phucvr.abc.shrc.fossilstechnicaltest1.util

import com.phucvr.abc.shrc.fossilstechnicaltest1.R

class Settings {
    companion object {
        val STATE_ICON_OF_CARD_FOLDER = hashMapOf("Download" to R.drawable.ic_download,
            "DCIM" to R.drawable.ic_camera,
            "Document" to R.drawable.ic_document,
            "Movies" to R.drawable.ic_play,
            "Music" to R.drawable.ic_audio,
            "Pictures" to R.drawable.ic_image)

        val STATE_ICON_OF_CARD_FILE = hashMapOf(
            "mp4" to R.drawable.ic_baseline_videocam,
            "mp3" to R.drawable.ic_baseline_music_video_24,
            "zip" to R.drawable.ic_outline_folder_zip)

        val EXTENSION_PICTURE = listOf<String>("jpg","png")

        val LIST_FOLDER_ESSENTIAL = listOf<String>("DCIM","Document","Download","Movies","Music","Notifications","Pictures","Recordings","Ringtones")

        const val OPTION_EDIT = "Edit"
        const val OPTION_VIEW = "View"
        const val OPTION_CREATE_FOLDER = "Trash"
        const val OPTION_SETTINGS = "Settings"

        val LIST_MENU_OPTION = listOf<String>(OPTION_EDIT)
    }
}

enum class TypeSort {
    NAME,DATE,TYPE,SIZE
}

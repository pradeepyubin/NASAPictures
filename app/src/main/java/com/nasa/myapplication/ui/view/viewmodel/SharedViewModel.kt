package com.nasa.myapplication.ui.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.nasa.myapplication.R
import com.nasa.myapplication.data.Picture
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.util.*

class SharedViewModel(private val app:Application): AndroidViewModel(app) {

    private var _pictureList = MutableLiveData<List<Picture>>()
    val pictureList: LiveData<List<Picture>> = _pictureList

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            _pictureList.value = getRawFileAsList(app.resources.openRawResource(R.raw.data))
        }
    }

    private fun getRawFileAsList(fileInputStream: InputStream): List<Picture> {
        val contentAsString = getFileContentFromStream(fileInputStream)
        return Gson().fromJson(contentAsString, Array<Picture>::class.java).toList()
    }

    private fun getFileContentFromStream(inputStream: InputStream): String {
        val outputStream = ByteArrayOutputStream()
        val buf = ByteArray(1024)
        var len: Int
        while (inputStream.read(buf).also { len = it } != -1) {
            outputStream.write(buf, 0, len)
        }
        outputStream.close()
        inputStream.close()
        return outputStream.toString()
    }
}
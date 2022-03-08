# Kotlin Coroutines Cheat Sheet



## Setup



## Basics

    GlobalScope.launch(Dispatchers.IO) {
        ...
    }
    
    runBlocking {
        ...
    }

### For Android

    lifecycleScope.launch {
        ...
    }



## How To

### How to handle errors

    val errorHandler = CoroutineExceptionHandler { _, exception ->
        AlertDialog.Builder(this).setTitle("Error")
            .setMessage(exception.message)
            .setPositiveButton(android.R.string.ok) { _, _ -> }
            .setIcon(android.R.drawable.ic_dialog_alert).show()
    }
    coroutineScope.launch(errorHandler) {
        ...
    }


## Resources
- 

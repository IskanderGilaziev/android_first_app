package com.example.crimeintent

import android.app.Application

class CriminalIntentApplication: Application() {
    //Как и Activity.onCreate (…), Application.onCreate () вызывается системой при первой загрузке приложения в память.
    // Это делает его хорошим местом для выполнения разовых операций инициализации.
    //Экземпляр приложения не будет постоянно уничтожен и воссоздан, как ваша деятельность или фрагменты классов.
    // Он создается при запуске приложения и уничтожается при разрушении процесса приложения.
    //Передаем экземпляр приложения в наш репозиторий как объект Context.
    // Этот объект действителен, пока наш процесс приложения находится в памяти, поэтому безопасно хранить ссылку на него в классе репозитория.
    // В манифесте добавить:
    // @param android:name=".CriminalIntentApplication"
    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this)
    }

}

/*
Когда класс приложения зарегистрирован в манифесте, ОС при запуске приложения создаст экземпляр CriminalIntentApplication.
Затем ОС вызовет onCreate () для экземпляра CriminalIntentApplication.
Ваш CrimeRepository будет инициализирован, и вы сможете получить к нему доступ из других ваших компонентов.
*/
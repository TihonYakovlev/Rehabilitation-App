package com.tihonyakovlev.rehabilitationapp.data

import com.tihonyakovlev.rehabilitationapp.data.entities.Disease
import com.tihonyakovlev.rehabilitationapp.data.entities.Section

object InitialData {
    val sections = listOf(
        Section(id = 1, name = "Нервная система"),
        Section(id = 2, name = "Опорно-двигательный аппарат и ортопедия"),
        Section(id = 3, name = "Желудочно-кишечный тракт"),
        Section(id = 4, name = "Кардиология")
    )

    val diseases = listOf(
        Disease(
            id = 1,
            sectionId = 3,
            name = "Язва двенадцатиперстной кишки",
            descriptionFile = "duodenal_ulcer/description.json",
            exercisesFile = "duodenal_ulcer/exercises.json"
        ),
        Disease(
            id = 2,
            sectionId = 2,
            name = "Артрит",
            descriptionFile = "arthritis/arthritis_description.json",
            exercisesFile = "arthritis/arthritis_exercises.json"
        ),
        Disease(
            id = 3,
            sectionId = 1,
            name = "Рассеянный склероз",
            descriptionFile = "multiple_sclerosis/description.json",
            exercisesFile = "multiple_sclerosis/exercises.json"
        ),
        Disease(
            id = 4,
            sectionId = 2,
            name = "Перелом лодыжки",
            descriptionFile = "ankle_fracture/description.json",
            exercisesFile = "ankle_fracture/exercises.json"
        ),
        Disease(
            id = 5,
            sectionId = 2,
            name = "Остеохондроз",
            descriptionFile = "osteochondrosis/description.json",
            exercisesFile = "osteochondrosis/exercises.json"
        ),
        Disease(
            id = 6,
            sectionId = 4,
            name = "Инфаркт",
            descriptionFile = "infarction/description_infraction.json",
            exercisesFile = "infarction/exercises.json"
        )
    )
}

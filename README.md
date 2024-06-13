# Аннотация
Настоящий документ является руководством пользователя по эксплуатации мобильного приложения **RehabilitationApp**.
В данном руководстве приводится следующая информация:

- Назначение и условия применения
- Подготовка к работе
- Описание операций
- Аварийные ситуации
- Рекомендации по освоению
- Термины и сокращения

Настоящий документ разработан в соответствии с ГОСТ 34 РД 50-34.698-90 «Автоматизированные системы. Требования к содержанию документов» — в части структуры и содержания документов, и в соответствии с ГОСТ 19 «Единая система программной документации (ЕСПД)» — в части общих требований и правил оформления программных документов.

---

# Введение
Приложение для реабилитации предоставляет широкий спектр функциональных возможностей для эффективного и удобного проведения реабилитационных программ. Основные функции включают:

- Персонализированные программы реабилитации: Создание индивидуальных программ упражнений и процедур на основе состояния пациента и рекомендаций врача.
- Мультимедийные инструкции: Включение видеороликов и изображений с пошаговыми инструкциями для выполнения упражнений.
- Мониторинг прогресса: Отслеживание прогресса пациента с помощью регулярных отчетов и оценки выполнения упражнений.

---

# Назначение и условия применения
Приложение предназначено для проведения реабилитационных мероприятий и восстановления пациентов после различных медицинских вмешательств и заболеваний. Оно может использоваться как в условиях медицинских учреждений, так и в домашних условиях. Приложение предоставляет индивидуальные программы реабилитации, мультимедийные инструкции по выполнению упражнений, а также средства для мониторинга и оценки прогресса.

**Условия применения:**

- Медицинские учреждения: Приложение может использоваться врачами и реабилитологами для назначения и контроля выполнения реабилитационных программ.
- Домашние условия: Пациенты могут использовать приложение самостоятельно или под контролем медицинских специалистов для выполнения рекомендованных упражнений и процедур.
- Спортивные учреждения: Приложение может применяться спортивными тренерами и физиотерапевтами для восстановления и поддержания физической формы спортсменов.

---

# Установка
- [ ] Скачивание приложения
- [ ] Установка
- [ ] Открытие приложения
- [ ] Настройка

---

# Описание операций

## 4.1 Просмотр информации о заболеваниях

1. **Наименование:**
   - Просмотр информации о заболеваниях
2. **Условия:**
   - Приложение должно быть установлено на устройстве пользователя.
   - Устройство должно быть включено и находиться в рабочем состоянии.
3. **Подготовительные действия:**
   - Открыть приложение, нажав на соответствующий значок на экране устройства.
4. **Основные действия в требуемой последовательности:**
   <ol><li>Откройте приложение на устройстве.</li>
   <li>На главном экране выберите интересующее заболевание из предложенного списка.</li>
   <li>Коснитесь выбранного заболевания, чтобы открыть подробное описание.</li>
   <li>Прочитайте информацию о симптомах, методах диагностики и лечении.</li>
   <li>Заключительные действия:</li>
   - Закрыть описание заболевания, вернувшись к главному экрану, или выйти из приложения.</ol>

5. **Исходный код экрана заболевания:**

    ```kotlin
    @Composable
    fun DiseaseScreen(diseaseId: Int) {
        Log.d("Disease Screen", "disease id on it screen: $diseaseId")

        val viewModel: DiseaseViewModel = getViewModel()
        val disease by viewModel.disease.collectAsState()
        val descriptionContent by viewModel.descriptionContent.collectAsState()
        val exercisesContent by viewModel.exercisesContent.collectAsState()

        LaunchedEffect(diseaseId) {
            viewModel.loadDiseaseById(diseaseId)
        }
        LaunchedEffect(disease) {
            disease?.let {
                Log.d("Disease Screen", "Loading content for disease: ${it.name}")
                viewModel.loadDiseaseContent(it.descriptionFile, it.exercisesFile)
            }
        }

        RehabilitationAppTheme {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            descriptionContent?.let {
                                val name = it.title
                                Text(name)
                            }
                        },
                        backgroundColor = MaterialTheme.colors.primary
                    )
                },
                content = {
                    if (descriptionContent == null && exercisesContent == null) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = MaterialTheme.colors.primary)
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            descriptionContent?.let {
                                items(it.content.size) { index ->
                                    val item = it.content[index]
                                    when (item.type) {
                                        "text" -> Text(
                                            text = item.value,
                                            style = MaterialTheme.typography.body1,
                                            modifier = Modifier.padding(vertical = 8.dp)
                                        )
                                        "image" -> ImageFromAssets(assetName = item.value)
                                    }
                                }
                            }
                            item {
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                            exercisesContent?.let {
                                items(it.content.size) { index ->
                                    val item = it.content[index]
                                    when (item.type) {
                                        "text" -> Text(
                                            text = item.value,
                                            style = MaterialTheme.typography.body1,
                                            modifier = Modifier.padding(vertical = 8.dp)
                                        )
                                        "image" -> ImageFromAssets(assetName = item.value)
                                    }
                                }
                            }
                        }
                    }
                }
            )
        }
    }

    @Composable
    fun ImageFromAssets(assetName: String) {
        val context = LocalContext.current
        val assetManager = context.assets
        val bitmap = remember(assetName) {
            val inputStream = assetManager.open(assetName)
            BitmapFactory.decodeStream(inputStream)
        }
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
    }
    ```

6. **Ресурсы:**
   - Время пользователя для просмотра информации.

### Изображение: Просмотр информации о заболеваниях

<img src="https://github.com/TihonYakovlev/Rehabilitation-App/assets/96542203/78dea17b-6891-4d63-9d08-7c36d0cd1c65" width="300">

---

## 4.2 Настройка темы оформления

1. **Наименование:**
   - Настройка темы оформления
2. **Условия:**
   - Приложение должно быть установлено на устройстве пользователя.
   - Устройство должно быть включено и находиться в рабочем состоянии.
3. **Подготовительные действия:**
   - Открыть приложение, нажав на соответствующий значок на экране устройства.
4. **Основные действия в требуемой последовательности:**
   <ol><li>Откройте приложение на устройстве.</li>
   <li>Перейдите в меню настроек, нажав на значок настроек (обычно в правом верхнем углу).</li>
   <li>Выберите раздел "Тема".</li>
   <li>Выберите желаемую тему оформления (светлая или тёмная).</li>
   <li>Нажмите кнопку "Сохранить" или "Применить", чтобы изменения вступили в силу.</li>
   <li>Заключительные действия:</li>
   - Вернуться на главный экран или выйти из меню настроек.</ol>

5. **Ресурсы:**
   - Время пользователя для настройки темы.

### Изображение: Настройка темы оформления

<img src="https://github.com/TihonYakovlev/Rehabilitation-App/assets/96542203/7d3701c0-0fac-482d-b77c-b29b56aeccda" width="300">

---

# Термины и сокращения

| **Термин** | **Полная форма**              | **Описание**                                                                 |
|------------|-------------------------------|------------------------------------------------------------------------------|
| **MVVM**   | (Model-View-ViewModel)        | Шаблон проектирования, который разделяет разработку пользовательского интерфейса и бизнес-логики. |
| **JSON**   | (JavaScript Object Notation)  | Формат обмена данными, используемый для передачи информации в приложении.    |
| **Room**   |                               | Библиотека для работы с локальной базой данных в приложениях на платформе Android. |
| **UI**     | (User Interface)              | Пользовательский интерфейс, элементы взаимодействия пользователя с приложением. |
| **SQLite** |                               | Встроенная реляционная база данных, используемая для хранения данных в мобильных приложениях. |

---

# Цитата
> "Не бойся, когда ты один, бойся, когда ты ноль"  
> > _Дочерняя цитата:_ "Muchas gracias aficion, estos para vosotros!"  

---

# Контакты
Для связи с разработчиками приложения, пожалуйста, обратитесь в [наш телеграм-канал](https://t.me/mohnatiy_perforator3000).

---

# Справочная информация

<div align="left">
<strong>Название приложения:</strong> RehabilitationApp  
<strong>Версия:</strong> 1.0  
<strong>Разработчик:</strong> Команда Tihon Yakovlev  
<strong>Поддержка:</strong> tijak280803@gmail.com  
</div>
<div align="right">
<strong>Дата выпуска:</strong> 2024  
<strong>Платформы:</strong> Android  
</div>

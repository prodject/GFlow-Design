================================================================================
          GFLOWD / MONJI: ИТОГОВЫЙ WORKFLOW И АРХИТЕКТУРА ИНТЕРФЕЙСА
          ГБ 13.2" Vertical (Android 11, Geely OneOS / ECARX / Zeekr)
================================================================================

1. ОБЩАЯ КОНЦЕПЦИЯ И UX-АРХИТЕКТУРА
--------------------------------------------------------------------------------
Интерфейс GFlowD разработан для 13.2" вертикальных сенсорных дисплеев ГУ.
Вместо перегруженных 26 разрозненных Activity реализована монолитная архитектура
Single-Activity (GFlowMainActivity) с 3-Зонной структурой в стиле Tesla и Material 3 Automotive:

  ┌──────────────────────────────────────────────────────────────────────────┐
  │ ZONE A: TOP STATUS BAR (Постоянный верхний бар: 64dp)                    │
  ├──────────────────────────────────────────────────────────────────────────┤
  │                                                                          │
  │ ZONE B: MAIN DYNAMIC WORKSPACE (Центральный холст: ~85% высоты)          │
  │                                                                          │
  │  4 ОСНОВНЫХ ХАБА (HUB SWITCHER):                                         │
  │   1. HOME & LAUNCHER (Интерактивный 2D холст Geely Cityray + Карточки)   │
  │   2. VEHICLE & DRIVE (Кузов, Освещение, Режимы движения, Сцены, HUD/DIM)  │
  │   3. CLIMATE & COMFORT (HVAC, Вентиляция, AQS, Просушка, Массаж сидений) │
  │   4. SAFETY & VISION (ADAS, 360° AVM Камеры, Прозрачный капот, Monji DVR)│
  │                                                                          │
  │  3 ВСПЛЫВАЮЩИХ ОВЕРЛЕЯ (SLIDE-UP SHEETS):                                │
  │   * AutomationOverlayView (Сценарии v2: Winter, Summer, Wash, Rain)    │
  │   * VoiceOverlayView (Офлайн распознавание речи Vosk + Голосовой свет)   │
  │   * SystemDrawerOverlay (ADB Shell, Autozoom DPI, AdaptAPI Диагностика) │
  │                                                                          │
  ├──────────────────────────────────────────────────────────────────────────┤
  │ ZONE C: PERSISTENT BOTTOM DOCK (Нижняя панель слепого управления: 96dp)  │
  │ (Размещается поверх / интегрирована с системным OneOS DockBar)          │
  └──────────────────────────────────────────────────────────────────────────┘


--------------------------------------------------------------------------------
2. ДЕТАЛЬНОЕ ОПИСАНИЕ 3 СТРУКТУРНЫХ ЗОН
--------------------------------------------------------------------------------

[ZONE A: TOP STATUS BAR (64dp)]
* Слева: Четкие часы (HH:mm) + Индикатор текущего Профиля Водителя (переключение в 1 клик).
* Центр: Погодный виджет Open-Meteo (температура за бортом) + Индикатор состояния Vosk Voice.
* Справа: Индикаторы Wi-Fi / Bluetooth / USB DVR + Быстрый вызов Системного Оверлея.

[ZONE B: MAIN DYNAMIC WORKSPACE]
Переключается кнопками управления или свайпами:

* HUB 1: Home & Launcher Workspace
  - Верхняя зона: 2D Canvas visualizer Geely Cityray (`GCityrayCarView`) с касаниями дверей, капота, багажника, люка и TPMS.
  - Нижняя зона: Сетка Tesla-карточек (Климат-пресеты, Плеер, Сцены, Статус ADAS).

* HUB 2: Vehicle, Body & Optics Control
  - Кузов & Двери: Ц. Замок, Защита детей, Панорамная шторка, Электро-багажник.
  - Освещение: Фары, Противотуманки, Атмосферная подсветка Ambience Light (синхронизация с музыкой).
  - Режимы движения: Eco, Comfort, Dynamic, Snow + Регулировка усилия на руле.
  - Авто-Сцены: Wash (Автомойка), Pet (Животное), Nap (Сон), Camping (Кемпинг).
  - HUD & DIM: Высота/наклон проекции, Snow Mode, темы приборной панели.

* HUB 3: Climate & Seat Comfort
  - Раздельная регулировка температуры (float), слайдер вентилятора (1-9 + Auto).
  - Направления обдува (Лицо, Ноги, Стекло), Рециркуляция (Авто/Внутр/Внеш).
  - Комфорт сидений: 3-уровневый подогрев, 3-уровневая вентиляция, Массаж (Волна/Пульс).
  - Качество воздуха: AQS sensor, CO2, Ионизатор G-Clean, Ароматизатор, Просушка A/C.
  - Smart Climate Presets: Fast Cool, Fast Heat, Stabilize, Dry, Summer.

* HUB 4: Safety & Vision Suite
  - Камера 360° AVM, Прозрачный капот, PAS динамическая траектория, сонары PDC, автопарковщик APA.
  - ADAS Активная безопасность: AEB, FCW, LKA/LDW, ELKA, RCW, BSD, Auto LCA, TSR знаки, ACC/ICC gap.
  - Monji DVR: Запись 1080p, выбор источника, лимиты памяти, SOS снимок/защита файла, выбор USB/Internal.

[ZONE C: PERSISTENT BOTTOM DOCK (96dp)]
Слепое управление без отвлечения от дороги (Hit target > 64dp):
  ┌─────────────────────────────────────────────────────────────────────────────────────────┐
  │ [DRIVER TEMP] [SEAT WARM] [DEFROST] [ CENTRAL GFLOWD HOME ] [REAR DEF] [PASS SEAT] [PASS TEMP]│
  │    21.5°C        [🔥 2]     [ ЛОБ ]    [  Cycle Hubs / Long ]   [ З А Д ]    [❄️ 1]     22.0°C │
  └─────────────────────────────────────────────────────────────────────────────────────────┘


--------------------------------------------------------------------------------
3. КАРТА ПОКРЫТИЯ ВСЕХ 15 ФУНКЦИОНАЛЬНЫХ ГРУПП ТЗ
--------------------------------------------------------------------------------
 1. Климат и Умный Климат     -> Hub 3 (ClimateHubView) + Zone C (GBottomDockView)
 2. Кузов, Двери и Освещение  -> Hub 2 (VehicleHubView) + Hub 1 (GCityrayCarView)
 3. Видеорегистратор DVR      -> Hub 4 (SafetyHubView)
 4. Ассистенты ADAS           -> Hub 4 (SafetyHubView)
 5. Парковка, 360° AVM & PAS  -> Hub 4 (SafetyHubView)
 6. Проектор HUD & DIM        -> Hub 2 (VehicleHubView)
 7. Голосовой ассистент Vosk  -> VoiceOverlayView + Zone A (GTopStatusBarView)
 8. Движок Автоматизации      -> AutomationOverlayView
 9. Профили Водителей         -> Zone A (GTopStatusBarView) + Hub 2 (VehicleHubView)
10. Мультируль & Нажатия      -> GBottomDockView + GFlowMainActivity
11. Рабочий стол & Dock       -> Hub 1 (HomeHubView) + SystemDrawerOverlay (Split Screen)
12. Погода & Геолокация       -> Zone A (GTopStatusBarView) + HomeHubView
13. Файлы & Медиаплеер        -> SystemDrawerOverlay + SafetyHubView
14. ADB Shell & Autozoom      -> SystemDrawerOverlay (ADB Shell, Autozoom, AdaptAPI Test)
15. Авто-Сцены & Ambience     -> Hub 2 (VehicleHubView)


--------------------------------------------------------------------------------
4. ИСХОДНЫЕ ФАЙЛЫ И СТРУКТУРА КОДА ПРОЕКТА GFLOWD
--------------------------------------------------------------------------------
GFlowD/
 ├── WORKFLOW.md (Текущий файл архитектуры)
 ├── build.gradle & settings.gradle
 └── app/src/main/
      ├── AndroidManifest.xml
      ├── res/values/themes.xml
      └── java/com/prodject/gflow/
           ├── GFlowMainActivity.java       (Единая главная Activity)
           ├── ui/theme/
           │    ├── GColors.java             (Дизайн-токены цветов)
           │    ├── GDimens.java             (Размеры хит-таргетов >64dp, DP/SP)
           │    └── GTypography.java         (Автомобильные шрифты)
           ├── ui/views/
           │    ├── GTopStatusBarView.java   (Верхняя панель Zone A)
           │    ├── GBottomDockView.java     (Нижняя панель Zone C)
           │    ├── GCityrayCarView.java     (2D Canvas модель Geely Cityray)
           │    ├── GCardView.java           (Tesla-style карточки)
           │    ├── GToggleTileView.java     (Автомобильные тумблеры)
           │    └── GSliderView.java         (Утолщенный сенсорный слайдер)
           ├── ui/hubs/
           │    ├── HomeHubView.java         (Hub 1: Home/Launcher)
           │    ├── VehicleHubView.java      (Hub 2: Кузов, Оптика, Режимы, Сцены, HUD)
           │    ├── ClimateHubView.java      (Hub 3: Климат, Сиденья, AQS, Просушка)
           │    └── SafetyHubView.java       (Hub 4: ADAS, 360° AVM, Monji DVR)
           └── ui/overlays/
                ├── AutomationOverlayView.java (Сценарии v2)
                ├── VoiceOverlayView.java      (Vosk Голосовой оверлей)
                └── SystemDrawerOverlay.java  (ADB Shell, Autozoom, AdaptAPI)
================================================================================

package eu.kanade.domain.library.service

import eu.kanade.tachiyomi.data.preference.DEVICE_ONLY_ON_WIFI
import eu.kanade.tachiyomi.data.preference.MANGA_HAS_UNREAD
import eu.kanade.tachiyomi.data.preference.MANGA_NON_COMPLETED
import eu.kanade.tachiyomi.data.preference.MANGA_NON_READ
import tachiyomi.core.preference.PreferenceStore
import tachiyomi.core.preference.getEnum
import tachiyomi.domain.entries.TriStateFilter
import tachiyomi.domain.entries.anime.model.Anime
import tachiyomi.domain.entries.manga.model.Manga
import tachiyomi.domain.library.anime.model.AnimeLibrarySort
import tachiyomi.domain.library.manga.model.MangaLibrarySort
import tachiyomi.domain.library.model.LibraryDisplayMode

class LibraryPreferences(
    private val preferenceStore: PreferenceStore,
) {

    // Common options

    fun bottomNavStyle() = preferenceStore.getInt("bottom_nav_style", 0)

    fun isDefaultHomeTabLibraryManga() = preferenceStore.getBoolean("default_home_tab_library", false)

    fun libraryDisplayMode() = preferenceStore.getObject("pref_display_mode_library", LibraryDisplayMode.default, LibraryDisplayMode.Serializer::serialize, LibraryDisplayMode.Serializer::deserialize)

    fun libraryMangaSortingMode() = preferenceStore.getObject("library_sorting_mode", MangaLibrarySort.default, MangaLibrarySort.Serializer::serialize, MangaLibrarySort.Serializer::deserialize)

    fun libraryAnimeSortingMode() = preferenceStore.getObject("animelib_sorting_mode", AnimeLibrarySort.default, AnimeLibrarySort.Serializer::serialize, AnimeLibrarySort.Serializer::deserialize)

    fun libraryUpdateInterval() = preferenceStore.getInt("pref_library_update_interval_key", 24)

    fun libraryUpdateLastTimestamp() = preferenceStore.getLong("library_update_last_timestamp", 0L)

    fun libraryUpdateDeviceRestriction() = preferenceStore.getStringSet("library_update_restriction", setOf(DEVICE_ONLY_ON_WIFI))

    fun libraryUpdateItemRestriction() = preferenceStore.getStringSet("library_update_manga_restriction", setOf(MANGA_HAS_UNREAD, MANGA_NON_COMPLETED, MANGA_NON_READ))

    fun autoUpdateMetadata() = preferenceStore.getBoolean("auto_update_metadata", false)

    fun autoUpdateTrackers() = preferenceStore.getBoolean("auto_update_trackers", false)

    fun showContinueViewingButton() = preferenceStore.getBoolean("display_continue_reading_button", false)

    // Common Category

    fun categoryTabs() = preferenceStore.getBoolean("display_category_tabs", true)

    fun categoryNumberOfItems() = preferenceStore.getBoolean("display_number_of_items", false)

    fun categorizedDisplaySettings() = preferenceStore.getBoolean("categorized_display", false)

    // Common badges

    fun downloadBadge() = preferenceStore.getBoolean("display_download_badge", false)

    fun localBadge() = preferenceStore.getBoolean("display_local_badge", true)

    fun languageBadge() = preferenceStore.getBoolean("display_language_badge", false)

    fun newShowUpdatesCount() = preferenceStore.getBoolean("library_show_updates_count", true)

    // Common Cache

    fun autoClearItemCache() = preferenceStore.getBoolean("auto_clear_chapter_cache", false)

    // Mixture Columns

    fun animePortraitColumns() = preferenceStore.getInt("pref_animelib_columns_portrait_key", 0)
    fun mangaPortraitColumns() = preferenceStore.getInt("pref_library_columns_portrait_key", 0)

    fun animeLandscapeColumns() = preferenceStore.getInt("pref_animelib_columns_landscape_key", 0)
    fun mangaLandscapeColumns() = preferenceStore.getInt("pref_library_columns_landscape_key", 0)

    // Mixture Filter

    fun filterDownloadedAnime() = preferenceStore.getEnum("pref_filter_animelib_downloaded_v2", TriStateFilter.DISABLED)
    fun filterDownloadedManga() = preferenceStore.getEnum("pref_filter_library_downloaded_v2", TriStateFilter.DISABLED)

    fun filterUnseen() = preferenceStore.getEnum("pref_filter_animelib_unread_v2", TriStateFilter.DISABLED)
    fun filterUnread() = preferenceStore.getEnum("pref_filter_library_unread_v2", TriStateFilter.DISABLED)

    fun filterStartedAnime() = preferenceStore.getEnum("pref_filter_animelib_started_v2", TriStateFilter.DISABLED)
    fun filterStartedManga() = preferenceStore.getEnum("pref_filter_library_started_v2", TriStateFilter.DISABLED)

    fun filterBookmarkedAnime() = preferenceStore.getEnum("pref_filter_animelib_bookmarked_v2", TriStateFilter.DISABLED)
    fun filterBookmarkedManga() = preferenceStore.getEnum("pref_filter_library_bookmarked_v2", TriStateFilter.DISABLED)

    fun filterCompletedAnime() = preferenceStore.getEnum("pref_filter_animelib_completed_v2", TriStateFilter.DISABLED)
    fun filterCompletedManga() = preferenceStore.getEnum("pref_filter_library_completed_v2", TriStateFilter.DISABLED)

    fun filterTrackedAnime(id: Int) = preferenceStore.getEnum("pref_filter_animelib_tracked_${id}_v2", TriStateFilter.DISABLED)
    fun filterTrackedManga(id: Int) = preferenceStore.getEnum("pref_filter_library_tracked_${id}_v2", TriStateFilter.DISABLED)

    // Mixture Update Count

    fun newMangaUpdatesCount() = preferenceStore.getInt("library_unread_updates_count", 0)
    fun newAnimeUpdatesCount() = preferenceStore.getInt("library_unseen_updates_count", 0)

    // Mixture Category

    fun defaultAnimeCategory() = preferenceStore.getInt("default_anime_category", -1)
    fun defaultMangaCategory() = preferenceStore.getInt("default_category", -1)

    fun lastUsedAnimeCategory() = preferenceStore.getInt("last_used_anime_category", 0)
    fun lastUsedMangaCategory() = preferenceStore.getInt("last_used_category", 0)

    fun animeLibraryUpdateCategories() = preferenceStore.getStringSet("animelib_update_categories", emptySet())
    fun mangaLibraryUpdateCategories() = preferenceStore.getStringSet("library_update_categories", emptySet())

    fun animeLibraryUpdateCategoriesExclude() = preferenceStore.getStringSet("animelib_update_categories_exclude", emptySet())
    fun mangaLibraryUpdateCategoriesExclude() = preferenceStore.getStringSet("library_update_categories_exclude", emptySet())

    // Mixture Item

    fun filterEpisodeBySeen() = preferenceStore.getLong("default_episode_filter_by_seen", Anime.SHOW_ALL)
    fun filterChapterByRead() = preferenceStore.getLong("default_chapter_filter_by_read", Manga.SHOW_ALL)

    fun filterEpisodeByDownloaded() = preferenceStore.getLong("default_episode_filter_by_downloaded", Anime.SHOW_ALL)
    fun filterChapterByDownloaded() = preferenceStore.getLong("default_chapter_filter_by_downloaded", Manga.SHOW_ALL)

    fun filterEpisodeByBookmarked() = preferenceStore.getLong("default_episode_filter_by_bookmarked", Anime.SHOW_ALL)
    fun filterChapterByBookmarked() = preferenceStore.getLong("default_chapter_filter_by_bookmarked", Manga.SHOW_ALL)

    // and upload date
    fun sortEpisodeBySourceOrNumber() = preferenceStore.getLong("default_episode_sort_by_source_or_number", Anime.EPISODE_SORTING_SOURCE)
    fun sortChapterBySourceOrNumber() = preferenceStore.getLong("default_chapter_sort_by_source_or_number", Manga.CHAPTER_SORTING_SOURCE)

    fun displayEpisodeByNameOrNumber() = preferenceStore.getLong("default_chapter_display_by_name_or_number", Anime.EPISODE_DISPLAY_NAME)
    fun displayChapterByNameOrNumber() = preferenceStore.getLong("default_chapter_display_by_name_or_number", Manga.CHAPTER_DISPLAY_NAME)

    fun sortEpisodeByAscendingOrDescending() = preferenceStore.getLong("default_chapter_sort_by_ascending_or_descending", Anime.EPISODE_SORT_DESC)
    fun sortChapterByAscendingOrDescending() = preferenceStore.getLong("default_chapter_sort_by_ascending_or_descending", Manga.CHAPTER_SORT_DESC)

    fun setEpisodeSettingsDefault(anime: Anime) {
        filterEpisodeBySeen().set(anime.unseenFilterRaw)
        filterEpisodeByDownloaded().set(anime.downloadedFilterRaw)
        filterEpisodeByBookmarked().set(anime.bookmarkedFilterRaw)
        sortEpisodeBySourceOrNumber().set(anime.sorting)
        displayEpisodeByNameOrNumber().set(anime.displayMode)
        sortEpisodeByAscendingOrDescending().set(if (anime.sortDescending()) Anime.EPISODE_SORT_DESC else Anime.EPISODE_SORT_ASC)
    }

    fun setChapterSettingsDefault(manga: Manga) {
        filterChapterByRead().set(manga.unreadFilterRaw)
        filterChapterByDownloaded().set(manga.downloadedFilterRaw)
        filterChapterByBookmarked().set(manga.bookmarkedFilterRaw)
        sortChapterBySourceOrNumber().set(manga.sorting)
        displayChapterByNameOrNumber().set(manga.displayMode)
        sortChapterByAscendingOrDescending().set(if (manga.sortDescending()) Manga.CHAPTER_SORT_DESC else Manga.CHAPTER_SORT_ASC)
    }
}

package eu.kanade.domain.base

import android.content.Context
import eu.kanade.tachiyomi.core.preference.Preference
import eu.kanade.tachiyomi.core.preference.PreferenceStore
import eu.kanade.tachiyomi.core.preference.getEnum
import eu.kanade.tachiyomi.data.preference.PreferenceValues.ExtensionInstaller
import eu.kanade.tachiyomi.util.system.hasMiuiPackageInstaller
import eu.kanade.tachiyomi.util.system.isShizukuInstalled
import kotlinx.coroutines.CoroutineScope

class ExtensionInstallerPreference(
    private val context: Context,
    preferenceStore: PreferenceStore,
) : Preference<ExtensionInstaller> {

    private val basePref = preferenceStore.getEnum(key(), defaultValue())

    override fun key() = "extension_installer"

    val entries get() = ExtensionInstaller.values().run {
        if (context.hasMiuiPackageInstaller) {
            filter { it != ExtensionInstaller.PACKAGEINSTALLER }
        } else {
            toList()
        }
    }

    override fun defaultValue() = if (context.hasMiuiPackageInstaller) {
        ExtensionInstaller.LEGACY
    } else {
        ExtensionInstaller.PACKAGEINSTALLER
    }

    private fun check(value: ExtensionInstaller): ExtensionInstaller {
        when (value) {
            ExtensionInstaller.PACKAGEINSTALLER -> {
                if (context.hasMiuiPackageInstaller) return ExtensionInstaller.LEGACY
            }
            ExtensionInstaller.SHIZUKU -> {
                if (!context.isShizukuInstalled) return defaultValue()
            }
            else -> {}
        }
        return value
    }

    override fun get(): ExtensionInstaller {
        val value = basePref.get()
        val checkedValue = check(value)
        if (value != checkedValue) {
            basePref.set(checkedValue)
        }
        return checkedValue
    }

    override fun set(value: ExtensionInstaller) {
        basePref.set(check(value))
    }

    override fun isSet() = basePref.isSet()

    override fun delete() = basePref.delete()

    override fun changes() = basePref.changes()

    override fun stateIn(scope: CoroutineScope) = basePref.stateIn(scope)
}

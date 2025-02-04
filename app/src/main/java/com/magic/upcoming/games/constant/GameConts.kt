package com.magic.upcoming.games.constant

import com.magic.upcoming.games.R

const val NO_IMG_FILE_NAME = "gb_default-16_9"

val currentGenerationPlatformRange = IntRange(0, 14)

object GameConts {
    const val BASE_PATH = "https://fy.iciba.com/"
    const val GIANT_BOMB_BASE_PATH = "https://www.giantbomb.com/api/"
    const val GIANT_BOMB_API_KEY = "c0d838fadae058f620be7fdaffbbbd8c7ad29d12"
}

enum class ReleaseDateType {
    RecentAndUpcoming,
    PastMonth,
    PastYear,
    Any,
    CustomDate
}

enum class SortDirection(val direction: String) {
    Ascending("asc"),
    Descending("desc")
}

enum class PlatformType {
    CurrentGeneration,
    All,
    PickFromList
}

data class Platform(val abbreviation: String, val fullName: String)

val allKnownPlatforms = arrayListOf(
        Platform("PC", "PC"),
        Platform("PS5", "PlayStation 5"),
        Platform("XSX", "Xbox Series X"),
        Platform("XONE", "Xbox One"),
        Platform("PS4", "PlayStation 4"),
        Platform("NSW", "Nintendo Switch"),
        Platform("IPHN", "iPhone"),
        Platform("ANDR", "Android"),
        Platform("3DS", "Nintendo 3DS"),
        Platform("APTV", "Apple TV"),
        Platform("3DSE", "3DS eShop"),
        Platform("N3DS", "New 3DS"),
        Platform("MAC", "Mac"),
        Platform("IPAD", "iPad"),
        Platform("STAD", "Stadia"),
        Platform("ARC", "Arcade"),
        Platform("BROW", "Browser"),
        Platform("X360", "Xbox 360"),
        Platform("WiiU", "Wii U"),
        Platform("PS3", "PlayStation 3"),
        Platform("Wii", "Wii"),
        Platform("XBGS", "360 Games Store"),
        Platform("PS3N", "PS3 Network"),
        Platform("VITA", "PS Vita"),
        Platform("PSNV", "PS Vita Network"),
        Platform("WSHP", "Wii Shop"),
        Platform("PSP", "PS Portable"),
        Platform("PSPN", "PSP Network"),
        Platform("DS", "Nintendo DS"),
        Platform("DSI", "DSiWare"),
        Platform("PS2", "PlayStation 2"),
        Platform("GCN", "GameCube"),
        Platform("XBOX", "Xbox"),
        Platform("DC", "Dreamcast"),
        Platform("PS1", "PlayStation"),
        Platform("SAT", "Saturn"),
        Platform("N64", "Nintendo 64"),
        Platform("SNES", "SNES"),
        Platform("GEN", "Genesis"),
        Platform("GBA", "Game Boy Advance"),
        Platform("GBC", "Game Boy Color"),
        Platform("GG", "Game Gear"),
        Platform("NES", "NES"),
        Platform("GB", "Game Boy"),
        Platform("SMS", "Master System"),
        Platform("2600", "Atari 2600"),
        Platform("NEO", "Neo Geo"),
        Platform("3DO", "3DO"),
        Platform("CDI", "CD-i"),
        Platform("JAG", "Jaguar"),
        Platform("SCD", "Sega CD"),
        Platform("32X", "Sega 32X"),
        Platform("NGE", "N-Gage"),
        Platform("C64", "Commodore 64"),
        Platform("MSX", "MSX"),
        Platform("SPEC", "ZX Spectrum"),
        Platform("CVIS", "ColecoVision"),
        Platform("INTV", "Intellivision"),
        Platform("TGCD", "TurboGrafx-CD"),
        Platform("TG16", "TurboGrafx-16"),
        Platform("VBOY", "Virtual Boy"),
        Platform("NGP", "Neo Geo Pocket"),
        Platform("NGPC", "NG Pocket Color"),
        Platform("LIN", "Linux"),
        Platform("JCD", "Jaguar CD"),
        Platform("OUYA", "Ouya"),
        Platform("FIRE", "Amazon Fire TV"),
        Platform("WP", "Windows Phone"),
        Platform("AMI", "Amiga"),
        Platform("LYNX", "Atari Lynx"),
        Platform("CPC", "Amstrad CPC"),
        Platform("APL2", "Apple II"),
        Platform("AST", "Atari ST"),
        Platform("A800", "Atari 8-bit"),
        Platform("VC20", "VIC-20"),
        Platform("A2GS", "Apple IIgs"),
        Platform("CD32", "Amiga CD32"),
        Platform("TI99", "TI-99/4A"),
        Platform("WSC", "WonderSwan Color"),
        Platform("C128", "Commodore 128"),
        Platform("NGCD", "Neo Geo CD"),
        Platform("ODY2", "Odyssey 2"),
        Platform("DRAG", "Dragon 32/64"),
        Platform("CBM", "Commodore PET"),
        Platform("TRS8", "TRS-80"),
        Platform("ZOD", "Zodiac"),
        Platform("WSW", "WonderSwan"),
        Platform("CHNF", "Channel F"),
        Platform("5200", "Atari 5200"),
        Platform("COCO", "TRS-80 CoCo"),
        Platform("7800", "Atari 7800"),
        Platform("IPOD", "iPod"),
        Platform("ODYS", "Odyssey"),
        Platform("PCFX", "PC-FX"),
        Platform("VECT", "Vectrex"),
        Platform("GCOM", "Game.Com"),
        Platform("GIZ", "Gizmondo"),
        Platform("VSML", "V.Smile"),
        Platform("PIN", "Pinball"),
        Platform("NUON", "NUON"),
        Platform("LEAP", "Leapster"),
        Platform("MVIS", "Microvision"),
        Platform("FDS", "Famicom Disk"),
        Platform("LACT", "LaserActive"),
        Platform("AVIS", "Adventure Vision"),
        Platform("X68K", "Sharp X68000"),
        Platform("BS-X", "Satellaview"),
        Platform("A2K1", "Arcadia 2001"),
        Platform("AQUA", "Aquarius"),
        Platform("64DD", "Nintendo 64DD"),
        Platform("PIPN", "Pippin"),
        Platform("RZON", "R-Zone"),
        Platform("HSCN", "HyperScan"),
        Platform("GWAV", "Game Wave"),
        Platform("HALC", "RDI Halcyon"),
        Platform("FMT", "FM Towns"),
        Platform("PC88", "NEC PC-8801"),
        Platform("BBCM", "BBC Micro"),
        Platform("PLTO", "PLATO"),
        Platform("PC98", "NEC PC-9801"),
        Platform("X1", "Sharp X1"),
        Platform("FM7", "FM-7"),
        Platform("6001", "NEC PC-6001"),
        Platform("PICO", "Sega Pico"),
        Platform("SGFX", "SuperGrafx"),
        Platform("BAST", "Bally Astrocade"),
        Platform("ZBO", "Zeebo"),
        Platform("ACRN", "Acorn Archimedes"),
        Platform("LOOP", "Casio Loopy"),
        Platform("PDIA", "Bandai Playdia"),
        Platform("MZ", "Sharp MZ"),
        Platform("RCA2", "RCA Studio II"),
        Platform("XAVX", "XaviXPORT"),
        Platform("GP32", "GamePark 32"),
        Platform("PMIN", "Pokemon mini"),
        Platform("CASV", "Epoch"),
        Platform("SCV", "SCV"),
        Platform("DUCK", "Mega Duck"),
        Platform("SG1K", "Sega SG-1000"),
        Platform("CDTV", "Commodore CDTV"),
        Platform("DIDJ", "Leapfrog Didj"),
        Platform("SVIS", "Supervision"),
        Platform("AMAX", "Action Max"),
        Platform("PV1K", "Casio PV-1000"),
        Platform("C16", "Commodore 16"),
        Platform("ACAN", "Super A'Can"),
        Platform("VIS", "Memorex MD 2500"),
        Platform("HGM", "Game Master"),
        Platform("SMC7", "Sony SMC-777"),
        Platform("COUP", "SAM Coupe"),
        Platform("VMIV", "View-Master"),
        Platform("TF1", "Fuze Tomahawk F1"),
        Platform("TUT", "Tomy Tutor"),
        Platform("GMT", "Gamate"),
        Platform("MBEE", "MicroBee"),
        Platform("VSOC", "VTech Socrates"),
        Platform("ABC", "Luxor ABC80"),
        Platform("ALXA", "Amazon Alexa"),
        Platform("ML1", "Magic Leap One"),
        Platform("BNA", "Beena"),
        Platform("OQST", "Oculus Quest"),
        Platform("PLDT", "Playdate"),
        Platform("EVER", "Evercade"),
        Platform("AMIC", "Amico"),
        Platform("NONE", "No platforms"))

data class Avatar(val image:Int)

val allDefaultAvatar = arrayListOf(
        Avatar(R.drawable.avatar1),
        Avatar(R.drawable.avatar2),
        Avatar(R.drawable.avatar3),
        Avatar(R.drawable.avatar4),
        Avatar(R.drawable.avatar5),
        Avatar(R.drawable.avatar6),
        Avatar(R.drawable.avatar7),
        Avatar(R.drawable.avatar8),
        Avatar(R.drawable.avatar9),
        Avatar(R.drawable.avatar10),
        Avatar(R.drawable.avatar11),
        Avatar(R.drawable.avatar12),
        Avatar(R.drawable.avatar13),
        Avatar(R.drawable.avatar14),
        Avatar(R.drawable.avatar15),
        Avatar(R.drawable.avatar16),
        Avatar(R.drawable.avatar17),
        Avatar(R.drawable.avatar18),
        Avatar(R.drawable.avatar19),
        Avatar(R.drawable.avatar20)
)

enum class DateFormat(val formatCode: Int) {
    Exact(0),
    Month(1),
    Quarter(2),
    Year(3),
    None(4)
}

enum class NetworkState {
    Loading,
    Failure,
    Success
}

enum class IconType(val type:String) {
    Game("game"),
    Video("video")
}


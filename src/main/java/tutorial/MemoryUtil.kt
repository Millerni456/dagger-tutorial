package tutorial

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter



fun printMemoryUsage(title: String = "MEMORY STATISTICS") {
    val usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()
    val r = Runtime.getRuntime()

    val dtf = DateTimeFormatter.ofPattern("yyyy/M/d H:mm:ss.SSSSSS")
    println("---------------------------------------------------")
    println(":  $title")
    println(":  Time: ${dtf.format(LocalDateTime.now())}")
    println("---------------------------------------------------")
    println(":  Memory Allocated: ${pb(r.totalMemory())}")
    println(":  Memory Usage:     ${pb(usedMemory)} / ${pb(r.totalMemory())}")
    println(":  Free Memory:      ${pb(r.freeMemory())} remaining")
    println(":  Maximum Memory Allowed: ${pb(r.maxMemory())}")
    println("")
}

private const val KILO = 1000L
private const val MEGA = 1000000L
private const val GIGA = 1000000000L

private fun pb(value: Long): String {
    fun round(v: Double, precision: Int = 2): Double {
        val scale = Math.pow(10.0, precision.toDouble())
        return Math.round(v * scale) /  scale
    }

    return when {
        (value >= GIGA) -> "${round(value.toDouble() / GIGA)}GB"
        (value >= MEGA) -> "${round(value.toDouble() / MEGA)}MB"
        (value >= KILO) -> "${round(value.toDouble() / KILO)}KB"
        else -> "${round(value.toDouble())}B"
    }
}
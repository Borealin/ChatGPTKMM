package cn.borealin.chatgptkmm.shared.cache.db

import cn.borealin.chatgptkmm.shared.expect.DatabaseDriverFactory


internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
//            dbQuery.removeAllRockets()
//            dbQuery.removeAllLaunches()
        }
    }

//    internal fun getAllLaunches(): List<RocketLaunch> {
//        return dbQuery.selectAllLaunchesInfo(::mapLaunchSelecting).executeAsList()
//    }
//
//    internal fun createLaunches(launches: List<RocketLaunch>) {
//        dbQuery.transaction {
//            launches.forEach { launch ->
//                val rocket = dbQuery.selectRocketById(launch.rocket.id).executeAsOneOrNull()
//                if (rocket == null) {
//                    insertRocket(launch)
//                }
//
//                insertLaunch(launch)
//            }
//        }
//    }

}
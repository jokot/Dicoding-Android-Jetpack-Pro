package com.jokot.app.moviecatalogue.utils

import com.jokot.app.moviecatalogue.data.source.local.entity.*
import com.jokot.app.moviecatalogue.data.source.remote.response.*

object DataDummy {

    fun generateDummyConfig(): ImageEntity {
        return ImageEntity(
            listOf("w300", "w780", "w1280", "original")[1],
            listOf("w92", "w154", "w185", "w342", "w500", "w780", "original")[0],
            "https://image.tmdb.org/t/p/"
        )
    }

    fun generateDummyMovieWithDetail(movieId: Int): MovieEntity {
        val movieEntity = generateDummyMovies()[0]
        movieEntity.movieDetailEntity = generateDummyMovieDetail()
        return movieEntity
    }

    fun generateDummyTvShowWithDetail(tvShowId: Int): TvShowEntity {
        val tvShowEntity = generateDummyTvShows()[0]
        tvShowEntity.tvShowDetailEntity = generateDummyTvShowDetail()
        return tvShowEntity
    }

    fun generateDummyMovieDetail(): MovieDetailEntity {
        return MovieDetailEntity(
            "1h 50m",
            listOf(
                "Action",
                "Fantasy",
                "Adventure",
            ).joinToString()
        )
    }

    fun generateDummyTvShowDetail(): TvShowDetailEntity {

        return TvShowDetailEntity(
            "44m",
            listOf(
                "Drama",
                "Sci-Fi & Fantasy"
            ).joinToString()
        )
    }

    fun generateDummyMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                1,
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "Apr 07, 2021",
                77,
                "https://www.themoviedb.org/t/p/w1280/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/6ELCZlTA5lGUops70hKdB83WJxH.jpg"
            )
        )
        movies.add(
            MovieEntity(
                2,
                "Godzilla vs. Kong",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "Mar 24, 2021",
                81,
                "https://www.themoviedb.org/t/p/w1280/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg"
            )
        )
        movies.add(
            MovieEntity(
                3,
                "Nobody",
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \"nobody.\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
                "Mar 26, 2021",
                84,
                "https://www.themoviedb.org/t/p/w1280/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/6zbKgwgaaCyyBXE4Sun4oWQfQmi.jpg"
            )
        )
        movies.add(
            MovieEntity(
                4,
                "22 vs. Earth",
                "Set before the events of ‘Soul’, 22 refuses to go to Earth, enlisting a gang of 5 new souls in attempt of rebellion. However, 22’s subversive plot leads to a surprising revelation about the meaning of life.",
                "Apr 30, 2021",
                72,
                "https://www.themoviedb.org/t/p/w1280/32vLDKSzcCMh55zqqaSqqUA8naz.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/n2y7T8wJVjJ8yLhQXQgNCpsC3ga.jpg"
            )
        )
        movies.add(
            MovieEntity(
                5,
                "Zack Snyder's Justice League",
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                "Mar 18, 2021",
                85,
                "https://www.themoviedb.org/t/p/w1280/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg"
            )
        )
        movies.add(
            MovieEntity(
                6,
                "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
                "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
                "Oct 16, 2020",
                84,
                "https://www.themoviedb.org/t/p/w1280/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/xPpXYnCWfjkt3zzE0dpCNME1pXF.jpg"
            )
        )
        movies.add(
            MovieEntity(
                7,
                "Raya and the Last Dragon",
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
                "Mar 03, 2021",
                82,
                "https://www.themoviedb.org/t/p/w1280/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/7prYzufdIOy1KCTZKVWpjBFqqNr.jpg"
            )
        )
        movies.add(
            MovieEntity(
                8,
                "Vanquish",
                "Victoria is a young mother trying to put her dark past as a Russian drug courier behind her, but retired cop Damon forces Victoria to do his bidding by holding her daughter hostage. Now, Victoria must use guns, guts and a motorcycle to take out a series of violent gangsters—or she may never see her child again.",
                "Apr 16, 2021",
                64,
                "https://www.themoviedb.org/t/p/w1280/AoWY1gkcNzabh229Icboa1Ff0BM.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/mYM8x2Atv4MaLulaV0KVJWI1Djv.jpg"
            )
        )
        movies.add(
            MovieEntity(
                9,
                "The Unholy",
                "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
                "Mar 31, 2021",
                56,
                "https://www.themoviedb.org/t/p/w1280/b4gYVcl8pParX8AjkN90iQrWrWO.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/lkInRiMtLgl9u9xE0By5hqf66K8.jpg"
            )
        )
        movies.add(
            MovieEntity(
                10,
                "Silk Road",
                "The true story of Ross Ulbricht, the charismatic young tech-mastermind who unleashed the darknet website Silk Road, and the corrupt DEA agent determined to bring down his billion-dollar empire.",
                "Feb 19, 2021",
                68,
                "https://www.themoviedb.org/t/p/w1280/6KxiEWyIDpz1ikmD7nv3GTX4Uoj.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/sLK03Ed0D8EOoqLFBjrOcYSz8Tm.jpg"
            )
        )
        movies.add(
            MovieEntity(
                1,
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "Apr 07, 2021",
                77,
                "https://www.themoviedb.org/t/p/w1280/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/6ELCZlTA5lGUops70hKdB83WJxH.jpg"
            )
        )
        movies.add(
            MovieEntity(
                2,
                "Godzilla vs. Kong",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "Mar 24, 2021",
                81,
                "https://www.themoviedb.org/t/p/w1280/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg"
            )
        )
        movies.add(
            MovieEntity(
                3,
                "Nobody",
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \"nobody.\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
                "Mar 26, 2021",
                84,
                "https://www.themoviedb.org/t/p/w1280/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/6zbKgwgaaCyyBXE4Sun4oWQfQmi.jpg"
            )
        )
        movies.add(
            MovieEntity(
                4,
                "22 vs. Earth",
                "Set before the events of ‘Soul’, 22 refuses to go to Earth, enlisting a gang of 5 new souls in attempt of rebellion. However, 22’s subversive plot leads to a surprising revelation about the meaning of life.",
                "Apr 30, 2021",
                72,
                "https://www.themoviedb.org/t/p/w1280/32vLDKSzcCMh55zqqaSqqUA8naz.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/n2y7T8wJVjJ8yLhQXQgNCpsC3ga.jpg"
            )
        )
        movies.add(
            MovieEntity(
                5,
                "Zack Snyder's Justice League",
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                "Mar 18, 2021",
                85,
                "https://www.themoviedb.org/t/p/w1280/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg"
            )
        )
        movies.add(
            MovieEntity(
                6,
                "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
                "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
                "Oct 16, 2020",
                84,
                "https://www.themoviedb.org/t/p/w1280/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/xPpXYnCWfjkt3zzE0dpCNME1pXF.jpg"
            )
        )
        movies.add(
            MovieEntity(
                7,
                "Raya and the Last Dragon",
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
                "Mar 03, 2021",
                82,
                "https://www.themoviedb.org/t/p/w1280/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/7prYzufdIOy1KCTZKVWpjBFqqNr.jpg"
            )
        )
        movies.add(
            MovieEntity(
                8,
                "Vanquish",
                "Victoria is a young mother trying to put her dark past as a Russian drug courier behind her, but retired cop Damon forces Victoria to do his bidding by holding her daughter hostage. Now, Victoria must use guns, guts and a motorcycle to take out a series of violent gangsters—or she may never see her child again.",
                "Apr 16, 2021",
                64,
                "https://www.themoviedb.org/t/p/w1280/AoWY1gkcNzabh229Icboa1Ff0BM.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/mYM8x2Atv4MaLulaV0KVJWI1Djv.jpg"
            )
        )
        movies.add(
            MovieEntity(
                9,
                "The Unholy",
                "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
                "Mar 31, 2021",
                56,
                "https://www.themoviedb.org/t/p/w1280/b4gYVcl8pParX8AjkN90iQrWrWO.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/lkInRiMtLgl9u9xE0By5hqf66K8.jpg"
            )
        )
        movies.add(
            MovieEntity(
                10,
                "Silk Road",
                "The true story of Ross Ulbricht, the charismatic young tech-mastermind who unleashed the darknet website Silk Road, and the corrupt DEA agent determined to bring down his billion-dollar empire.",
                "Feb 19, 2021",
                68,
                "https://www.themoviedb.org/t/p/w1280/6KxiEWyIDpz1ikmD7nv3GTX4Uoj.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/sLK03Ed0D8EOoqLFBjrOcYSz8Tm.jpg"
            )
        )
        return movies
    }

    fun generateDummyTvShows(): List<TvShowEntity> {
        val tvShows = ArrayList<TvShowEntity>()

        tvShows.add(
            TvShowEntity(
                1,
                "The Falcon and the Winter Soldier",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "Mar 19, 2021",
                79,
                "https://www.themoviedb.org/t/p/w1280/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                2,
                "The Good Doctor",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "Sep 25, 2017",
                86,
                "https://www.themoviedb.org/t/p/w1280/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/zlXPNnnUlyg6KyvvjGd2ZxG6Tnw.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                3,
                "Invincible",
                "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                "Mar 26, 2021",
                89,
                "https://www.themoviedb.org/t/p/w1280/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                4,
                "The Bad Batch",
                "Follow the elite and experimental Clones of the Bad Batch as they find their way in a rapidly changing galaxy in the aftermath of the Clone Wars.",
                "May 04, 2021",
                90,
                "https://www.themoviedb.org/t/p/w1280/WjQmEWFrOf98nT5aEfUfVYz9N2.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/sjxtIUCWR74yPPcZFfTsToepfWm.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                5,
                "The Walking Dead",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "Oct 31, 2010",
                81,
                "https://www.themoviedb.org/t/p/w1280/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/uro2Khv7JxlzXtLb8tCIbRhkb9E.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                6,
                "WandaVision",
                "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                "Jan 15, 2021",
                84,
                "https://www.themoviedb.org/t/p/w1280/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/1i1N0AVRb54H6ZFPDTwbo9MLxSF.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                7,
                "Van Helsing",
                "Vanessa Helsing, the daughter of famous vampire hunter and Dracula nemesis Abraham Van Helsing is resurrected five years in the future to find out that vampires have taken over the world and that she possesses unique power over them. She is humanity’s last hope to lead an offensive to take back what has been lost.",
                "Sep 23, 2016",
                69,
                "https://www.themoviedb.org/t/p/w1280/qMC4nnMZfnFMVnxRtN4Rey3NDXb.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/5VltHQJXdmbSD6gEJw3R8R1Kbmc.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                8,
                "Game of Thrones",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "Apr 17, 2011",
                84,
                "https://www.themoviedb.org/t/p/w1280/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/suopoADq0k8YZr4dQXcU6pToj6s.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                9,
                "Fear the Walking Dead",
                "What did the world look like as it was transforming into the horrifying apocalypse depicted in \"The Walking Dead\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.",
                "Aug 23, 2015",
                76,
                "https://www.themoviedb.org/t/p/w1280/4UjiPdFKJGJYdxwRs2Rzg7EmWqr.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/58PON1OrnBiX6CqEHgeWKVwrCn6.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                10,
                "Jupiter's Legacy",
                "When the world's first generation of superheroes received their powers in the 1930s become the revered elder guard in the present, their superpowered children struggle to live up to the legendary feats of their parents.",
                "May 07, 2021",
                75,
                "https://www.themoviedb.org/t/p/w1280/9yxep7oJdkj3Pla9TD9gKflRApY.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/4YKkS95v9o9c0tBVA46xIn6M1tx.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                1,
                "The Falcon and the Winter Soldier",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "Mar 19, 2021",
                79,
                "https://www.themoviedb.org/t/p/w1280/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                2,
                "The Good Doctor",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "Sep 25, 2017",
                86,
                "https://www.themoviedb.org/t/p/w1280/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/zlXPNnnUlyg6KyvvjGd2ZxG6Tnw.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                3,
                "Invincible",
                "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                "Mar 26, 2021",
                89,
                "https://www.themoviedb.org/t/p/w1280/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                4,
                "The Bad Batch",
                "Follow the elite and experimental Clones of the Bad Batch as they find their way in a rapidly changing galaxy in the aftermath of the Clone Wars.",
                "May 04, 2021",
                90,
                "https://www.themoviedb.org/t/p/w1280/WjQmEWFrOf98nT5aEfUfVYz9N2.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/sjxtIUCWR74yPPcZFfTsToepfWm.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                5,
                "The Walking Dead",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "Oct 31, 2010",
                81,
                "https://www.themoviedb.org/t/p/w1280/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/uro2Khv7JxlzXtLb8tCIbRhkb9E.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                6,
                "WandaVision",
                "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                "Jan 15, 2021",
                84,
                "https://www.themoviedb.org/t/p/w1280/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/1i1N0AVRb54H6ZFPDTwbo9MLxSF.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                7,
                "Van Helsing",
                "Vanessa Helsing, the daughter of famous vampire hunter and Dracula nemesis Abraham Van Helsing is resurrected five years in the future to find out that vampires have taken over the world and that she possesses unique power over them. She is humanity’s last hope to lead an offensive to take back what has been lost.",
                "Sep 23, 2016",
                69,
                "https://www.themoviedb.org/t/p/w1280/qMC4nnMZfnFMVnxRtN4Rey3NDXb.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/5VltHQJXdmbSD6gEJw3R8R1Kbmc.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                8,
                "Game of Thrones",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "Apr 17, 2011",
                84,
                "https://www.themoviedb.org/t/p/w1280/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/suopoADq0k8YZr4dQXcU6pToj6s.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                9,
                "Fear the Walking Dead",
                "What did the world look like as it was transforming into the horrifying apocalypse depicted in \"The Walking Dead\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.",
                "Aug 23, 2015",
                76,
                "https://www.themoviedb.org/t/p/w1280/4UjiPdFKJGJYdxwRs2Rzg7EmWqr.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/58PON1OrnBiX6CqEHgeWKVwrCn6.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                10,
                "Jupiter's Legacy",
                "When the world's first generation of superheroes received their powers in the 1930s become the revered elder guard in the present, their superpowered children struggle to live up to the legendary feats of their parents.",
                "May 07, 2021",
                75,
                "https://www.themoviedb.org/t/p/w1280/9yxep7oJdkj3Pla9TD9gKflRApY.jpg",
                "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces/4YKkS95v9o9c0tBVA46xIn6M1tx.jpg"
            )
        )

        return tvShows
    }

    fun generateRemoteDummyConfig(): ImagesResponse {
        return ImagesResponse(
            listOf("w300", "w780", "w1280", "original"),
            "http://image.tmdb.org/t/p/",
            listOf("w92", "w154", "w185", "w342", "w500", "w780", "original"),
            listOf("w45", "w185", "h632", "original"),
            "https://image.tmdb.org/t/p/"
        )
    }

    fun generateRemoteDummyMovie(): List<MovieResponse> {
        val movies = ArrayList<MovieResponse>()

        movies.add(
            MovieResponse(
                false,
                "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
                listOf(28, 14, 12),
                460465,
                "en",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                2437.17,
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat",
                false,
                7.6,
                2594
            )
        )
        movies.add(
            MovieResponse(
                false,
                "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
                listOf(28, 14, 12),
                460465,
                "en",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                2437.17,
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat",
                false,
                7.6,
                2594
            )
        )
        movies.add(
            MovieResponse(
                false,
                "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
                listOf(28, 14, 12),
                460465,
                "en",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                2437.17,
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat",
                false,
                7.6,
                2594
            )
        )
        movies.add(
            MovieResponse(
                false,
                "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
                listOf(28, 14, 12),
                460465,
                "en",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                2437.17,
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat",
                false,
                7.6,
                2594
            )
        )
        movies.add(
            MovieResponse(
                false,
                "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
                listOf(28, 14, 12),
                460465,
                "en",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                2437.17,
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat",
                false,
                7.6,
                2594
            )
        )
        movies.add(
            MovieResponse(
                false,
                "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
                listOf(28, 14, 12),
                460465,
                "en",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                2437.17,
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat",
                false,
                7.6,
                2594
            )
        )
        movies.add(
            MovieResponse(
                false,
                "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
                listOf(28, 14, 12),
                460465,
                "en",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                2437.17,
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat",
                false,
                7.6,
                2594
            )
        )
        movies.add(
            MovieResponse(
                false,
                "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
                listOf(28, 14, 12),
                460465,
                "en",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                2437.17,
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat",
                false,
                7.6,
                2594
            )
        )
        movies.add(
            MovieResponse(
                false,
                "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
                listOf(28, 14, 12),
                460465,
                "en",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                2437.17,
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat",
                false,
                7.6,
                2594
            )
        )
        movies.add(
            MovieResponse(
                false,
                "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
                listOf(28, 14, 12),
                460465,
                "en",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                2437.17,
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat",
                false,
                7.6,
                2594
            )
        )
        movies.add(
            MovieResponse(
                false,
                "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
                listOf(28, 14, 12),
                460465,
                "en",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                2437.17,
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat",
                false,
                7.6,
                2594
            )
        )
        movies.add(
            MovieResponse(
                false,
                "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
                listOf(28, 14, 12),
                460465,
                "en",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                2437.17,
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat",
                false,
                7.6,
                2594
            )
        )
        movies.add(
            MovieResponse(
                false,
                "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
                listOf(28, 14, 12),
                460465,
                "en",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                2437.17,
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat",
                false,
                7.6,
                2594
            )
        )
        movies.add(
            MovieResponse(
                false,
                "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
                listOf(28, 14, 12),
                460465,
                "en",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                2437.17,
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat",
                false,
                7.6,
                2594
            )
        )
        movies.add(
            MovieResponse(
                false,
                "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
                listOf(28, 14, 12),
                460465,
                "en",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                2437.17,
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat",
                false,
                7.6,
                2594
            )
        )
        movies.add(
            MovieResponse(
                false,
                "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
                listOf(28, 14, 12),
                460465,
                "en",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                2437.17,
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat",
                false,
                7.6,
                2594
            )
        )
        movies.add(
            MovieResponse(
                false,
                "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
                listOf(28, 14, 12),
                460465,
                "en",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                2437.17,
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat",
                false,
                7.6,
                2594
            )
        )
        movies.add(
            MovieResponse(
                false,
                "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
                listOf(28, 14, 12),
                460465,
                "en",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                2437.17,
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat",
                false,
                7.6,
                2594
            )
        )
        movies.add(
            MovieResponse(
                false,
                "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
                listOf(28, 14, 12),
                460465,
                "en",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                2437.17,
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat",
                false,
                7.6,
                2594
            )
        )
        movies.add(
            MovieResponse(
                false,
                "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
                listOf(28, 14, 12),
                460465,
                "en",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                2437.17,
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat",
                false,
                7.6,
                2594
            )
        )

        return movies
    }

    fun generateRemoteDummyTvShow(): List<TvShowResponse> {
        val tvShows = ArrayList<TvShowResponse>()

        tvShows.add(
            TvShowResponse(
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "2014-10-07",
                listOf(18, 10765),
                60735,
                "The Falcon and the Winter Soldier",
                listOf("US"),
                "en",
                "The Falcon and the Winter Soldier",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Falcon and the Winter Soldier.",
                1136.942,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7,
                7659
            )
        )
        tvShows.add(
            TvShowResponse(
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "2014-10-07",
                listOf(18, 10765),
                60735,
                "The Falcon and the Winter Soldier",
                listOf("US"),
                "en",
                "The Falcon and the Winter Soldier",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Falcon and the Winter Soldier.",
                1136.942,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7,
                7659
            )
        )
        tvShows.add(
            TvShowResponse(
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "2014-10-07",
                listOf(18, 10765),
                60735,
                "The Falcon and the Winter Soldier",
                listOf("US"),
                "en",
                "The Falcon and the Winter Soldier",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Falcon and the Winter Soldier.",
                1136.942,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7,
                7659
            )
        )
        tvShows.add(
            TvShowResponse(
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "2014-10-07",
                listOf(18, 10765),
                60735,
                "The Falcon and the Winter Soldier",
                listOf("US"),
                "en",
                "The Falcon and the Winter Soldier",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Falcon and the Winter Soldier.",
                1136.942,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7,
                7659
            )
        )
        tvShows.add(
            TvShowResponse(
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "2014-10-07",
                listOf(18, 10765),
                60735,
                "The Falcon and the Winter Soldier",
                listOf("US"),
                "en",
                "The Falcon and the Winter Soldier",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Falcon and the Winter Soldier.",
                1136.942,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7,
                7659
            )
        )
        tvShows.add(
            TvShowResponse(
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "2014-10-07",
                listOf(18, 10765),
                60735,
                "The Falcon and the Winter Soldier",
                listOf("US"),
                "en",
                "The Falcon and the Winter Soldier",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Falcon and the Winter Soldier.",
                1136.942,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7,
                7659
            )
        )
        tvShows.add(
            TvShowResponse(
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "2014-10-07",
                listOf(18, 10765),
                60735,
                "The Falcon and the Winter Soldier",
                listOf("US"),
                "en",
                "The Falcon and the Winter Soldier",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Falcon and the Winter Soldier.",
                1136.942,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7,
                7659
            )
        )
        tvShows.add(
            TvShowResponse(
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "2014-10-07",
                listOf(18, 10765),
                60735,
                "The Falcon and the Winter Soldier",
                listOf("US"),
                "en",
                "The Falcon and the Winter Soldier",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Falcon and the Winter Soldier.",
                1136.942,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7,
                7659
            )
        )
        tvShows.add(
            TvShowResponse(
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "2014-10-07",
                listOf(18, 10765),
                60735,
                "The Falcon and the Winter Soldier",
                listOf("US"),
                "en",
                "The Falcon and the Winter Soldier",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Falcon and the Winter Soldier.",
                1136.942,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7,
                7659
            )
        )
        tvShows.add(
            TvShowResponse(
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "2014-10-07",
                listOf(18, 10765),
                60735,
                "The Falcon and the Winter Soldier",
                listOf("US"),
                "en",
                "The Falcon and the Winter Soldier",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Falcon and the Winter Soldier.",
                1136.942,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7,
                7659
            )
        )
        tvShows.add(
            TvShowResponse(
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "2014-10-07",
                listOf(18, 10765),
                60735,
                "The Falcon and the Winter Soldier",
                listOf("US"),
                "en",
                "The Falcon and the Winter Soldier",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Falcon and the Winter Soldier.",
                1136.942,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7,
                7659
            )
        )
        tvShows.add(
            TvShowResponse(
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "2014-10-07",
                listOf(18, 10765),
                60735,
                "The Falcon and the Winter Soldier",
                listOf("US"),
                "en",
                "The Falcon and the Winter Soldier",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Falcon and the Winter Soldier.",
                1136.942,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7,
                7659
            )
        )
        tvShows.add(
            TvShowResponse(
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "2014-10-07",
                listOf(18, 10765),
                60735,
                "The Falcon and the Winter Soldier",
                listOf("US"),
                "en",
                "The Falcon and the Winter Soldier",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Falcon and the Winter Soldier.",
                1136.942,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7,
                7659
            )
        )
        tvShows.add(
            TvShowResponse(
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "2014-10-07",
                listOf(18, 10765),
                60735,
                "The Falcon and the Winter Soldier",
                listOf("US"),
                "en",
                "The Falcon and the Winter Soldier",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Falcon and the Winter Soldier.",
                1136.942,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7,
                7659
            )
        )
        tvShows.add(
            TvShowResponse(
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "2014-10-07",
                listOf(18, 10765),
                60735,
                "The Falcon and the Winter Soldier",
                listOf("US"),
                "en",
                "The Falcon and the Winter Soldier",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Falcon and the Winter Soldier.",
                1136.942,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7,
                7659
            )
        )
        tvShows.add(
            TvShowResponse(
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "2014-10-07",
                listOf(18, 10765),
                60735,
                "The Falcon and the Winter Soldier",
                listOf("US"),
                "en",
                "The Falcon and the Winter Soldier",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Falcon and the Winter Soldier.",
                1136.942,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7,
                7659
            )
        )
        tvShows.add(
            TvShowResponse(
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "2014-10-07",
                listOf(18, 10765),
                60735,
                "The Falcon and the Winter Soldier",
                listOf("US"),
                "en",
                "The Falcon and the Winter Soldier",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Falcon and the Winter Soldier.",
                1136.942,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7,
                7659
            )
        )
        tvShows.add(
            TvShowResponse(
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "2014-10-07",
                listOf(18, 10765),
                60735,
                "The Falcon and the Winter Soldier",
                listOf("US"),
                "en",
                "The Falcon and the Winter Soldier",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Falcon and the Winter Soldier.",
                1136.942,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7,
                7659
            )
        )
        tvShows.add(
            TvShowResponse(
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "2014-10-07",
                listOf(18, 10765),
                60735,
                "The Falcon and the Winter Soldier",
                listOf("US"),
                "en",
                "The Falcon and the Winter Soldier",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Falcon and the Winter Soldier.",
                1136.942,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7,
                7659
            )
        )
        tvShows.add(
            TvShowResponse(
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "2014-10-07",
                listOf(18, 10765),
                60735,
                "The Falcon and the Winter Soldier",
                listOf("US"),
                "en",
                "The Falcon and the Winter Soldier",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Falcon and the Winter Soldier.",
                1136.942,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7,
                7659
            )
        )

        return tvShows
    }

    fun generateRemoteDummyMovieDetail(): MovieDetailResponse {
        return MovieDetailResponse(
            false,
            "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
            20000000,
            listOf(
                GenreResponse(28, "Action"),
                GenreResponse(14, "Fantasy"),
                GenreResponse(12, "Adventure"),
            ),
            "https://www.mortalkombatmovie.net",
            460465,
            "tt0293429",
            "en",
            "Mortal Kombat",
            "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
            2437.17,
            "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
            "2021-04-07",
            76706000,
            110,
            "Released",
            "Get over here.",
            "Mortal Kombat",
            false,
            7.6,
            2603
        )
    }

    fun generateRemoteDummyTvShowDetail(): TvShowDetailResponse {
        val seasons = ArrayList<SeasonResponse>()
        seasons.add(
            SeasonResponse(
                "2016-04-19",
                8,
                79954,
                "Specials",
                "",
                "/ft8pUr3qX41kOux5eqrwf02yAxZ.jpg",
                0
            )
        )
        seasons.add(
            SeasonResponse(
                "2016-04-19",
                8,
                79954,
                "Specials",
                "",
                "/ft8pUr3qX41kOux5eqrwf02yAxZ.jpg",
                0
            )
        )
        seasons.add(
            SeasonResponse(
                "2016-04-19",
                8,
                79954,
                "Specials",
                "",
                "/ft8pUr3qX41kOux5eqrwf02yAxZ.jpg",
                0
            )
        )
        seasons.add(
            SeasonResponse(
                "2016-04-19",
                8,
                79954,
                "Specials",
                "",
                "/ft8pUr3qX41kOux5eqrwf02yAxZ.jpg",
                0
            )
        )
        seasons.add(
            SeasonResponse(
                "2016-04-19",
                8,
                79954,
                "Specials",
                "",
                "/ft8pUr3qX41kOux5eqrwf02yAxZ.jpg",
                0
            )
        )
        seasons.add(
            SeasonResponse(
                "2016-04-19",
                8,
                79954,
                "Specials",
                "",
                "/ft8pUr3qX41kOux5eqrwf02yAxZ.jpg",
                0
            )
        )
        seasons.add(
            SeasonResponse(
                "2016-04-19",
                8,
                79954,
                "Specials",
                "",
                "/ft8pUr3qX41kOux5eqrwf02yAxZ.jpg",
                0
            )
        )
        return TvShowDetailResponse(
            "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
            listOf(44),
            "2014-10-07",
            listOf(
                GenreResponse(18, "Drama"),
                GenreResponse(10765, "Sci-Fi & Fantasy")
            ),
            "http://www.cwtv.com/shows/the-flash/",
            60735,
            true,
            listOf("en"),
            "2021-05-18",
            "The Falcon and the Winter Soldier",
            146,
            7,
            "en",
            "The Falcon and the Winter Soldier",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Falcon and the Winter Soldier.",
            1136.942,
            "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
            seasons,
            "Returning Series",
            "The fastest man alive.",
            "Scripted",
            7.7,
            7662
        )
    }
}
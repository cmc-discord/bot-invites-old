/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package wiki.moderation.bot.invites

import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.utils.env
import com.kotlindiscord.kord.extensions.utils.envOrNull
import dev.kord.common.entity.Snowflake

val GUILD_ID = Snowflake(
	envOrNull("GUILD_ID")
		?: "1131360407727128576"
)

private val TOKEN = env("TOKEN")   // Get the bot' token from the env vars or a .env file

suspend fun main() {
	val bot = ExtensibleBot(TOKEN) {
		applicationCommands {
			defaultGuild(GUILD_ID)
		}

		extensions {
			sentry {
				enableIfDSN(envOrNull("SENTRY_DSN"))
			}
		}
	}

	bot.start()
}

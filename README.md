# Echoing Depths — Forge 1.20.1 (v2.0 source)

Source project prepared for GitHub Actions compilation.

## Core content
- Resonite Ore + Deepslate Resonite Ore and complete Resonite equipment set.
- Echo Compass and Resonant Shards.
- **Resonance Archivist** villager profession.
- **Resonance Table** workstation block. Unemployed villagers can claim it as a job site.
- Unique enchanted-book trades:
  - **Echo Strike I–III** — extra melee damage.
  - **Deep Step I–II** — movement speed while underground.
  - **Resonant Guard I–III** — reduces incoming damage.
- Full Resonite armor grants underground Night Vision.
- **Echo Stalker** cave monster.
- **Crystal Crawler** cave monster.
- **Echo Guardian** boss with 180 HP and a boss bar.
- **Echo Sigil** summons the Echo Guardian below Y=45.
- **Resonance Core** boss reward and progression item.
- **Echo Ruins** underground mini-dungeon with a loot chest and a rare Echo Sigil.
- New drops: Crystal Fang, Resonance Core.

## GitHub build
1. Create an empty GitHub repository.
2. Upload the **contents** of this folder to the repository root.
3. Push/commit to `main`.
4. Open **Actions** → **Build Echoing Depths**.
5. Download the `Echoing-Depths-Forge-1.20.1` artifact after a successful build.

Requires Java 17. Target: Minecraft 1.20.1 / Forge 47.2.0+.

## Boss Expansion
The progression now continues beyond Echo Guardian:

1. **Resonant Colossus** — 320 HP, heavy melee damage and periodic shockwave slams. Below 50% health its slam becomes stronger. Drops **Colossus Heart**.
2. **Abyss Herald** — 260 HP, ranged magic pulses that inflict Darkness and Slowness. Enrages below 40% health. Drops **Abyssal Eye**.
3. **Resonance Tyrant** — 500 HP final boss with three combat phases, defensive buffs, area magic and an aggressive final phase. Drops the **Tyrant Crown**.

Boss progression is chained through craftable sigils:
**Echo Guardian → Colossus Sigil → Resonant Colossus → Abyss Sigil → Abyss Herald → Tyrant Sigil → Resonance Tyrant**.

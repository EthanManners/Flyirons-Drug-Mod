# Fabric Rewrite Architecture Overview

## Module map
1. **content/** — curated `DrugDefinition` and later `ProductionChainDefinition` data objects (closed-box content).
2. **state/** — persistent per-player physiology (`tolerance`, `addiction`, `withdrawal`, `overdose`, `poly-use`).
3. **registry/ + block/** — custom crops, machines, and bespoke items.
4. **network/** — server-authoritative sync packets to client HUD/screens.
5. **client/** — keybound physiology UI, HUD, future psychedelic overlays/shaders/audio warping.

## Interaction flow
- Player consumes custom drug item -> server applies dose through physiology engine.
- Server updates persistent profile -> applies effects/risk -> syncs client snapshot packet.
- Client HUD + screen render current physiology safely (read-only, server authoritative).
- Production blocks convert sourced ingredients into packaged products feeding market loops.

# Milestone Build Plan
1. **Scaffold (done in this slice):** Fabric Loom project layout, shared registry patterns, networking channel.
2. **Vertical Slice (done in this slice):**
   - Cannabis item: `Green Haze Joint`
   - Stimulant item: `Spark Cart`
   - Crop: `Emberleaf`
   - Machine: `Compression Bench`
   - Packaging: `Emberleaf Bundle + Empty Cart Shell -> Spark Cart`
   - Persistent tolerance/addiction/withdrawal/overdose loop + HUD/screen
3. **Cannabis Deep Progression:** strain journal GUI, lineage/mutations, bong block durability, cart variants.
4. **Overdose Expansion:** staged crisis states, signatures per family, configurable lethality policy, anti-grief constraints.
5. **Recovery Economy:** family-specific cure chains, taper flow, emergency reset rarity, medic profession loops.
6. **World Integration:** biome-gated resources, machine heat/attention pressure, loot hooks and region specialization.
7. **Psychedelic Client Stack:** intensity-scaling visual/audio pipeline with accessibility toggles.
8. **Achievement UX:** milestone progression UI tied to use, survival, farming mastery, and full recovery arcs.

# Additional Fictional Production Professions (future)
- **Aether Distillers:** harvest storm-bloom crystals from high peaks; distill volatile "ion syrup" in coil towers.
- **Bog Mycologists:** cultivate phosphor caps in swamp substrate vats; risk spore blight contamination.
- **Ash Chem-techs:** mine ember salts from volcanic cracks; refine in thermal crucibles for stimulant catalysts.
- **Courier/Smuggler Gameplay:** high-value compact packages with route risk (attention/heat systems).

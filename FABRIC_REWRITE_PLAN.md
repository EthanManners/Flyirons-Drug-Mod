# Flyirons Drug Mod — Fabric Rewrite Plan

## Architecture Overview

- **Content Layer**: Curated `DrugDefinition` and future `ProductionChainDefinition` objects define drugs, family tags, potency curves, tolerance pressure, addiction pressure, withdrawal windows, and overdose pressure.
- **Systems Layer**: `PlayerPhysiologyManager` applies doses, runs tolerance/addiction decay, triggers withdrawal symptoms, and builds synced snapshots for UI/HUD.
- **World Layer**: Custom crops + machine blocks (vertical slice starts with `CannabisCropBlock` and `LeafDryerBlock`), plus recipes for packaging.
- **Persistence + Networking**: Server-authoritative state in `PlayerPhysiologyPersistentState`, synced via lightweight packets (`PHYSIOLOGY_SYNC`, `OPEN_DRUGS_MENU`).
- **Client Layer**: Dedicated client entrypoint, HUD telemetry, and `/drugs` menu screen.

## Milestones

1. **Scaffold (done in this slice)**
   - Fabric Loom project setup (1.21.1).
   - Main/client entrypoints and network channels.
   - New source-set split so legacy plugin code is not part of the Fabric build.

2. **Vertical Slice (done in this slice)**
   - 2 drugs: cannabis (`cannabis_bloom`) and stimulant (`spark_dust`, packaged variant).
   - 1 custom crop: cannabis crop + seeds + harvest item.
   - 1 machine: leaf dryer converts bud -> bloom and crude spark -> spark dust.
   - 1 packaging step: shapeless recipe for packaged stimulant.
   - Basic tolerance/addiction/withdrawal loop with persistent per-player per-drug state.
   - `/drugs` command opens client physiology monitor; HUD shows quick metrics.

3. **Overdose Staging Expansion**
   - Early warning / impairment / crisis phases per family.
   - Distinct signatures and configurable lethality policies.
   - Poly-use interaction model via recent-use stack.

4. **Recovery Gameplay Arc**
   - Family-specific recovery goods and stations.
   - Sleep/rest acceleration and taper-vs-reset decision points.
   - Rare emergency intervention line.

5. **Cannabis Endgame System**
   - Genetics model, lineage tree, mutation/environment influences.
   - Strain journal GUI + progression unlocks.
   - Bong block and cart durability systems.

6. **Production Professions + Economy Heat**
   - Additional fictionalized chains (fungal, mineral, biome-locked resources, machine risk/failure states).
   - Optional heat/attention pressure on industrial sprawl.

7. **Client Psychedelic Pass**
   - Intensity-scaled overlays, color transforms, trails, UI warp, and audio filters.
   - Accessibility toggles for motion/strobe reduction.

8. **Content Completion + Achievements**
   - Curated milestone achievements and achievement UI.
   - Full family roster and trade-role balancing.

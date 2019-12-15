#!/usr/bin/env python

import os
import json

dir = "src/main/resources/data/mysticalworld/loot_tables/entities"

for t in os.listdir(dir):
    if not t.endswith("json"):
        continue

    with open(os.path.join(dir, t)) as o:
        data = json.load(o)

    registry_names = []

    for pool in data["pools"]:
        for entry in pool["entries"]:
            if "item" not in entry["type"]:
                continue

            registry_names.append(entry["name"])

    print("""DROPS.put("%s", Arrays.asList(%s));""" % (t.replace(".json", "").replace("_inject", ""), ", ".join(["""new ResourceLocation("%s", "%s")""" % tuple(x.split(":")) for x in registry_names])))

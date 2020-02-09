function initializeCoreMod() {
    return {
        'spawnShoulderEntities': {
            'target': {
                'type': 'METHOD',
                'class': 'net.minecraft.entity.player.PlayerEntity',
                'methodName': 'func_192030_dh',
                'methodDesc': '()V'
            },
            'transformer': function (method) {
                print("[MysticalWorld] Patching PlayerEntity::spawnShoulderEntities...");

                var ASMAPI = Java.type('net.minecraftforge.coremod.api.ASMAPI');
                var Opcodes = Java.type('org.objectweb.asm.Opcodes');
                var VarInsnNode = Java.type('org.objectweb.asm.tree.VarInsnNode');
                var InsnNode = Java.type('org.objectweb.asm.tree.InsnNode');
                var FieldInsnNode = Java.type('org.objectweb.asm.tree.FieldInsnNode');
                var InsnList = Java.type('org.objectweb.asm.tree.InsnList');

                var instr = method.instructions;

                var insn = new InsnList();
                insn.add(new VarInsnNode(Opcodes.ALOAD, 0));
                insn.add(new FieldInsnNode(Opcodes.GETSTATIC, "net/minecraft/entity/player/PlayerEntity", "field_192032_bt", "Lnet/minecraft/network/datasync/DataParameter;"));
                insn.add(new FieldInsnNode(Opcodes.GETSTATIC, "net/minecraft/entity/player/PlayerEntity", "field_192033_bu", "Lnet/minecraft/network/datasync/DataParameter;"));
                insn.add(ASMAPI.buildMethodCall("epicsquid/mysticalworld/core/PlayerHooks", "spawnShoulderEntities", "(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/network/datasync/DataParameter;Lnet/minecraft/network/datasync/DataParameter;)V", ASMAPI.MethodType.STATIC));
                insn.add(new InsnNode(Opcodes.RETURN));
                instr.insert(insn);

                return method;
            }
        }
    }
}

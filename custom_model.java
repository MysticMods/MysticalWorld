// Made with Blockbench 4.0.2
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


public class custom_model extends EntityModel<Entity> {
	private final ModelRenderer player;
	private final ModelRenderer helmet;
	private final ModelRenderer top_horn_piece_r1;
	private final ModelRenderer right_strap_r1;
	private final ModelRenderer chest_armor;
	private final ModelRenderer right_top_strap_r1;
	private final ModelRenderer left_arm_armor;
	private final ModelRenderer left_top_horn_r1;
	private final ModelRenderer right_arm_armor;
	private final ModelRenderer right_top_horn_r1;
	private final ModelRenderer left_leg_armor;
	private final ModelRenderer right_leg_armor;
	private final ModelRenderer left_boot_armor;
	private final ModelRenderer left_plate_r1;
	private final ModelRenderer right_boot_armor;
	private final ModelRenderer right_plate_r1;

	public custom_model() {
		textureWidth = 64;
		textureHeight = 64;

		player = new ModelRenderer(this);
		player.setRotationPoint(-8.0F, 16.0F, 8.0F);
		player.setTextureOffset(32, 41).addBox(4.0F, -23.5F, -12.005F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		player.setTextureOffset(32, 41).addBox(4.0F, -15.5F, -10.005F, 8.0F, 12.0F, 4.0F, 0.0F, true);
		player.setTextureOffset(32, 41).addBox(12.0F, -15.5F, -10.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);
		player.setTextureOffset(32, 41).addBox(0.0F, -15.5F, -10.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		player.setTextureOffset(32, 41).addBox(4.0F, -3.5F, -10.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		player.setTextureOffset(32, 41).addBox(8.0F, -3.5F, -10.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		helmet = new ModelRenderer(this);
		helmet.setRotationPoint(0.0F, -2.0F, 1.0F);
		helmet.setTextureOffset(0, 0).addBox(-4.5F, -6.0F, -6.0F, 9.0F, 9.0F, 2.0F, 0.0F, false);
		helmet.setTextureOffset(6, 11).addBox(3.25F, -2.0F, -4.0F, 1.0F, 2.0F, 7.0F, 0.0F, false);
		helmet.setTextureOffset(0, 30).addBox(-4.25F, -2.0F, 2.25F, 8.0F, 2.0F, 1.0F, 0.0F, false);
		helmet.setTextureOffset(0, 11).addBox(-2.5F, 3.0F, -6.0F, 5.0F, 4.0F, 1.0F, 0.0F, false);

		top_horn_piece_r1 = new ModelRenderer(this);
		top_horn_piece_r1.setRotationPoint(0.0F, -9.25F, -2.75F);
		helmet.addChild(top_horn_piece_r1);
		setRotationAngle(top_horn_piece_r1, -0.3927F, 0.0F, 0.0F);
		top_horn_piece_r1.setTextureOffset(0, 25).addBox(-1.0F, -2.75F, 0.25F, 2.0F, 2.0F, 3.0F, 0.0F, false);
		top_horn_piece_r1.setTextureOffset(0, 16).addBox(-1.0F, -2.75F, -1.75F, 2.0F, 7.0F, 2.0F, 0.0F, false);

		right_strap_r1 = new ModelRenderer(this);
		right_strap_r1.setRotationPoint(-3.75F, -1.0F, -0.5F);
		helmet.addChild(right_strap_r1);
		setRotationAngle(right_strap_r1, 0.0F, 0.0F, -3.1416F);
		right_strap_r1.setTextureOffset(6, 11).addBox(-0.5F, -1.0F, -3.5F, 1.0F, 2.0F, 7.0F, 0.0F, false);

		chest_armor = new ModelRenderer(this);
		chest_armor.setRotationPoint(-2.0F, 5.0F, -2.0F);
		chest_armor.setTextureOffset(0, 33).addBox(-2.5F, -4.0F, -1.5F, 9.0F, 6.0F, 1.0F, 0.0F, false);
		chest_armor.setTextureOffset(0, 40).addBox(-1.0F, 2.0F, -1.0F, 6.0F, 5.0F, 1.0F, 0.0F, false);
		chest_armor.setTextureOffset(0, 53).addBox(-2.5F, -4.0F, 4.0F, 9.0F, 10.0F, 1.0F, 0.0F, false);
		chest_armor.setTextureOffset(8, 20).addBox(3.75F, -4.75F, -0.25F, 2.0F, 1.0F, 4.0F, 0.0F, false);
		chest_armor.setTextureOffset(0, 46).addBox(-2.25F, 3.0F, -0.25F, 8.0F, 2.0F, 4.0F, 0.0F, false);
		chest_armor.setTextureOffset(0, 46).addBox(-2.25F, -3.0F, -0.25F, 8.0F, 2.0F, 4.0F, 0.0F, false);

		right_top_strap_r1 = new ModelRenderer(this);
		right_top_strap_r1.setRotationPoint(-1.75F, -4.25F, 2.0F);
		chest_armor.addChild(right_top_strap_r1);
		setRotationAngle(right_top_strap_r1, 0.0F, 3.1416F, 0.0F);
		right_top_strap_r1.setTextureOffset(8, 20).addBox(-2.0F, -0.5F, -2.25F, 2.0F, 1.0F, 4.0F, 0.0F, false);

		left_arm_armor = new ModelRenderer(this);
		left_arm_armor.setRotationPoint(6.0F, 2.5F, 0.0F);
		left_arm_armor.setTextureOffset(15, 11).addBox(-2.25F, -2.25F, -1.25F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		left_arm_armor.setTextureOffset(22, 0).addBox(-1.0F, -2.25F, -2.5F, 3.0F, 6.0F, 5.0F, 0.0F, false);
		left_arm_armor.setTextureOffset(12, 22).addBox(1.25F, 3.75F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		left_arm_armor.setTextureOffset(38, 0).addBox(0.5F, 5.5F, -2.5F, 2.0F, 4.0F, 5.0F, 0.0F, false);
		left_arm_armor.setTextureOffset(14, 40).addBox(-0.5F, 9.5F, -2.5F, 3.0F, 1.0F, 5.0F, 0.0F, false);
		left_arm_armor.setTextureOffset(18, 18).addBox(-2.25F, 6.5F, -2.25F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		left_arm_armor.setTextureOffset(18, 18).addBox(-2.25F, 8.0F, -2.25F, 3.0F, 1.0F, 4.0F, 0.0F, false);

		left_top_horn_r1 = new ModelRenderer(this);
		left_top_horn_r1.setRotationPoint(2.5F, -2.5F, 1.5F);
		left_arm_armor.addChild(left_top_horn_r1);
		setRotationAngle(left_top_horn_r1, 0.0F, 0.0F, 0.3927F);
		left_top_horn_r1.setTextureOffset(10, 27).addBox(-2.0F, -2.0F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		left_top_horn_r1.setTextureOffset(22, 11).addBox(0.0F, -2.0F, -2.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);

		right_arm_armor = new ModelRenderer(this);
		right_arm_armor.setRotationPoint(-6.0F, 2.5F, 0.0F);
		right_arm_armor.setTextureOffset(15, 11).addBox(1.0F, -2.25F, -1.25F, 1.0F, 1.0F, 2.0F, 0.0F, true);
		right_arm_armor.setTextureOffset(22, 0).addBox(-2.5F, -2.25F, -2.5F, 3.0F, 6.0F, 5.0F, 0.0F, true);
		right_arm_armor.setTextureOffset(12, 22).addBox(-2.25F, 3.75F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, true);
		right_arm_armor.setTextureOffset(38, 0).addBox(-2.5F, 5.5F, -2.5F, 2.0F, 4.0F, 5.0F, 0.0F, true);
		right_arm_armor.setTextureOffset(14, 40).addBox(-2.5F, 9.5F, -2.5F, 3.0F, 1.0F, 5.0F, 0.0F, true);
		right_arm_armor.setTextureOffset(18, 18).addBox(-1.5F, 6.5F, -2.25F, 3.0F, 1.0F, 4.0F, 0.0F, true);
		right_arm_armor.setTextureOffset(18, 18).addBox(-1.5F, 8.0F, -2.25F, 3.0F, 1.0F, 4.0F, 0.0F, true);

		right_top_horn_r1 = new ModelRenderer(this);
		right_top_horn_r1.setRotationPoint(-2.5F, -2.5F, 1.5F);
		right_arm_armor.addChild(right_top_horn_r1);
		setRotationAngle(right_top_horn_r1, 0.0F, 0.0F, -0.3927F);
		right_top_horn_r1.setTextureOffset(10, 27).addBox(0.0F, -2.0F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, true);
		right_top_horn_r1.setTextureOffset(22, 11).addBox(-1.0F, -2.0F, -2.0F, 1.0F, 5.0F, 2.0F, 0.0F, true);

		left_leg_armor = new ModelRenderer(this);
		left_leg_armor.setRotationPoint(2.0F, 12.5F, 0.0F);
		left_leg_armor.setTextureOffset(28, 11).addBox(-1.0F, 0.25F, -2.5F, 3.0F, 4.0F, 5.0F, 0.0F, false);
		left_leg_armor.setTextureOffset(39, 11).addBox(-1.75F, 5.75F, -2.5F, 3.0F, 4.0F, 1.0F, 0.0F, false);
		left_leg_armor.setTextureOffset(16, 23).addBox(-2.25F, 1.5F, -2.25F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		left_leg_armor.setTextureOffset(19, 29).addBox(-2.25F, 6.25F, -2.25F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		left_leg_armor.setTextureOffset(19, 29).addBox(-2.25F, 7.75F, -2.25F, 4.0F, 1.0F, 4.0F, 0.0F, false);

		right_leg_armor = new ModelRenderer(this);
		right_leg_armor.setRotationPoint(-2.0F, 12.5F, 0.0F);
		right_leg_armor.setTextureOffset(28, 11).addBox(-2.5F, 0.25F, -2.5F, 3.0F, 4.0F, 5.0F, 0.0F, true);
		right_leg_armor.setTextureOffset(39, 11).addBox(-1.75F, 5.75F, -2.5F, 3.0F, 4.0F, 1.0F, 0.0F, true);
		right_leg_armor.setTextureOffset(16, 23).addBox(1.0F, 1.5F, -2.25F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		right_leg_armor.setTextureOffset(19, 29).addBox(-2.25F, 6.25F, -2.25F, 4.0F, 1.0F, 4.0F, 0.0F, true);
		right_leg_armor.setTextureOffset(19, 29).addBox(-2.25F, 7.75F, -2.25F, 4.0F, 1.0F, 4.0F, 0.0F, true);

		left_boot_armor = new ModelRenderer(this);
		left_boot_armor.setRotationPoint(2.0F, 12.5F, 0.0F);
		left_boot_armor.setTextureOffset(20, 46).addBox(-2.0F, 9.1F, 0.25F, 4.0F, 1.0F, 2.0F, 0.0F, false);
		left_boot_armor.setTextureOffset(20, 57).addBox(-2.0F, 10.1F, -2.75F, 4.0F, 2.0F, 5.0F, 0.0F, false);

		left_plate_r1 = new ModelRenderer(this);
		left_plate_r1.setRotationPoint(-2.0F, 12.75F, -5.0F);
		left_boot_armor.addChild(left_plate_r1);
		setRotationAngle(left_plate_r1, -1.9635F, 0.0F, 0.0F);
		left_plate_r1.setTextureOffset(20, 34).addBox(0.25F, -2.0F, -3.5F, 3.0F, 1.0F, 2.0F, 0.0F, false);

		right_boot_armor = new ModelRenderer(this);
		right_boot_armor.setRotationPoint(-2.0F, 12.5F, 0.0F);
		right_boot_armor.setTextureOffset(20, 46).addBox(-2.25F, 9.1F, 0.25F, 4.0F, 1.0F, 2.0F, 0.0F, true);
		right_boot_armor.setTextureOffset(20, 57).addBox(-2.25F, 10.1F, -2.75F, 4.0F, 2.0F, 5.0F, 0.0F, true);

		right_plate_r1 = new ModelRenderer(this);
		right_plate_r1.setRotationPoint(-0.125F, 11.0143F, -2.6575F);
		right_boot_armor.addChild(right_plate_r1);
		setRotationAngle(right_plate_r1, -1.9635F, 0.0F, 0.0F);
		right_plate_r1.setTextureOffset(20, 34).addBox(-1.875F, -0.5F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F, true);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		player.render(matrixStack, buffer, packedLight, packedOverlay);
		helmet.render(matrixStack, buffer, packedLight, packedOverlay);
		chest_armor.render(matrixStack, buffer, packedLight, packedOverlay);
		left_arm_armor.render(matrixStack, buffer, packedLight, packedOverlay);
		right_arm_armor.render(matrixStack, buffer, packedLight, packedOverlay);
		left_leg_armor.render(matrixStack, buffer, packedLight, packedOverlay);
		right_leg_armor.render(matrixStack, buffer, packedLight, packedOverlay);
		left_boot_armor.render(matrixStack, buffer, packedLight, packedOverlay);
		right_boot_armor.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
package li.cil.oc.client.renderer.entity

import li.cil.oc.client.Textures
import li.cil.oc.util.RenderState
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.entity.Render
import net.minecraft.entity.Entity
import org.lwjgl.opengl.GL11

object DroneRenderer extends Render(Minecraft.getMinecraft.getRenderManager) {
  val model = new ModelQuadcopter()

  override def doRender(entity: Entity, x: Double, y: Double, z: Double, yaw: Float, dt: Float) {
    bindEntityTexture(entity)
    RenderState.pushMatrix()
    RenderState.pushAttrib()

    GL11.glTranslated(x, y + 2 / 16f, z)

    model.render(entity, 0, 0, 0, 0, 0, dt)

    RenderState.popAttrib()
    RenderState.popMatrix()
  }

  override def getEntityTexture(entity: Entity) = Textures.Model.Drone
}

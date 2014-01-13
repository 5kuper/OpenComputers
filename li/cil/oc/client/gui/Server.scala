package li.cil.oc.client.gui

import li.cil.oc.Settings
import li.cil.oc.common.container
import li.cil.oc.common.inventory.ServerInventory
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.util.{ResourceLocation, StatCollector}
import org.lwjgl.opengl.GL11

class Server(playerInventory: InventoryPlayer, serverInventory: ServerInventory) extends DynamicGuiContainer(new container.Server(playerInventory, serverInventory)) {
  protected val serverBackground = new ResourceLocation(Settings.resourceDomain, "textures/gui/server.png")

  override def drawGuiContainerForegroundLayer(mouseX: Int, mouseY: Int) = {
    super.drawGuiContainerForegroundLayer(mouseX, mouseY)
    fontRenderer.drawString(
      StatCollector.translateToLocal(serverInventory.getInvName),
      8, 6, 0x404040)
  }

  override def drawGuiContainerBackgroundLayer(dt: Float, mouseX: Int, mouseY: Int) {
    GL11.glColor3f(1, 1, 1) // Required under Linux.
    super.drawGuiContainerBackgroundLayer(dt, mouseX, mouseY)
    mc.renderEngine.bindTexture(serverBackground)
    drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize)
  }

  override def doesGuiPauseGame = false
}
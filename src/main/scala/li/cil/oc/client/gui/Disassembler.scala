package li.cil.oc.client.gui

import li.cil.oc.Localization
import li.cil.oc.client.Textures
import li.cil.oc.client.gui.widget.ProgressBar
import li.cil.oc.common.container
import li.cil.oc.common.tileentity
import li.cil.oc.util.RenderState
import net.minecraft.entity.player.InventoryPlayer

class Disassembler(playerInventory: InventoryPlayer, val disassembler: tileentity.Disassembler) extends DynamicGuiContainer(new container.Disassembler(playerInventory, disassembler)) {
  private def disassemblerContainer = inventorySlots.asInstanceOf[container.Disassembler]

  val progress = addWidget(new ProgressBar(18, 65))

  override def drawSecondaryForegroundLayer(mouseX: Int, mouseY: Int) = {
    fontRendererObj.drawString(
      Localization.localizeImmediately(disassembler.getName),
      8, 6, 0x404040)
  }

  override def drawGuiContainerBackgroundLayer(dt: Float, mouseX: Int, mouseY: Int) {
    RenderState.color(1, 1, 1)
    Textures.bind(Textures.GUI.Disassembler)
    drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize)
    progress.level = disassemblerContainer.disassemblyProgress / 100.0
    drawWidgets()
  }
}

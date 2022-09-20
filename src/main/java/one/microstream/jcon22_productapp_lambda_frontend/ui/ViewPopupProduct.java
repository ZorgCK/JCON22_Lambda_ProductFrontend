
package one.microstream.jcon22_productapp_lambda_frontend.ui;

import java.math.BigDecimal;
import java.util.List;

import com.rapidclipse.framework.server.ui.ItemLabelGeneratorFactory;
import com.rapidclipse.framework.server.ui.UIUtils;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.FormItem;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.FileRejectedEvent;
import com.vaadin.flow.component.upload.SucceededEvent;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.RangeValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.function.SerializableRunnable;

import one.microstream.jcon22_productapp_lambda_frontend.dao.DAOCategories;
import one.microstream.jcon22_productapp_lambda_frontend.dao.DAOProducts;
import one.microstream.jcon22_productapp_lambda_frontend.domain.Category;
import one.microstream.jcon22_productapp_lambda_frontend.domain.Product;


public class ViewPopupProduct extends VerticalLayout
{
	private Product              product      = new Product();
	private final MemoryBuffer   memoryBuffer = new MemoryBuffer();
	private SerializableRunnable onOk         = null;
	
	/**
	 *
	 */
	public ViewPopupProduct(final Product product, final SerializableRunnable onOk)
	{
		super();
		this.onOk    = onOk;
		this.product = product;
		this.initUI();
		
		this.upload.setAcceptedFileTypes(".jpg", ".jpeg", ".png");
		this.upload.setReceiver(this.memoryBuffer);
		
		final int maxFileSize = 800 * 1024;
		this.upload.setMaxFileSize(maxFileSize);
		
		final List<Category> categories = DAOCategories.listCategories();
		
		this.comboBoxCategory.setItems(categories);

		this.binder.addValueChangeListener(vcl -> {
			this.binder.validate();
		});
		
		this.binder.readBean(product);
		final Category category = categories.stream().filter(c -> c.getId() == product.getCategoryId()).findAny().get();
		this.comboBoxCategory.setValue(category);
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #buttonSave}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void buttonSave_onClick(final ClickEvent<Button> event)
	{
		if(this.binder.writeBeanIfValid(this.product) == false)
		{
			Notification.show("Some Fields are still invalid!").setDuration(800);
		}
		else
		{
			if(this.product.getUuid() == null)
			{
				DAOProducts.insertProduct(this.product);
			}
			else
			{
				DAOProducts.updateProduct(this.product);
			}
			
			// iterate till open Dialog is found
			final Dialog parent = UIUtils.getNextParent(this, Dialog.class);
			parent.close();
			
			this.onOk.run();
		}
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #buttonCancel}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void buttonCancel_onClick(final ClickEvent<Button> event)
	{
		this.product = new Product();
		final Dialog parent = UIUtils.getNextParent(this, Dialog.class);
		parent.close();
	}
	
	/**
	 * Event handler delegate method for the {@link Upload} {@link #upload}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void upload_onSucceeded(final SucceededEvent event)
	{
		// try
		// {
		// this.product.setImageName(event.getFileName());
		// this.product.setImageBytes(IOUtils.toByteArray(this.memoryBuffer.getInputStream()));
		// }
		// catch(final IOException e)
		// {
		// e.printStackTrace();
		// }
	}
	
	/**
	 * Event handler delegate method for the {@link Upload} {@link #upload}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void upload_onFileRejected(final FileRejectedEvent event)
	{
		final Notification notification = Notification.show(
			event.getErrorMessage(),
			5000,
			Notification.Position.MIDDLE);
		notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
	}
	
	/* WARNING: Do NOT edit!<br>The content of this method is always regenerated by the UI designer. */
	// <generated-code name="initUI">
	private void initUI()
	{
		this.formLayout        = new FormLayout();
		this.formItem2         = new FormItem();
		this.labelName         = new Label();
		this.textFieldName     = new TextField();
		this.formItem3         = new FormItem();
		this.labelDesc         = new Label();
		this.textFieldDesc     = new TextField();
		this.formItem4         = new FormItem();
		this.labelCategory     = new Label();
		this.comboBoxCategory  = new ComboBox<>();
		this.formItem5         = new FormItem();
		this.labelPrice        = new Label();
		this.bigDecimalPrice   = new BigDecimalField();
		this.formItem6         = new FormItem();
		this.labelWeight       = new Label();
		this.numberFieldWeight = new NumberField();
		this.formItem7         = new FormItem();
		this.labelStock        = new Label();
		this.integerFieldStock = new IntegerField();
		this.formItem          = new FormItem();
		this.upload            = new Upload();
		this.label             = new Label();
		this.horizontalLayout  = new HorizontalLayout();
		this.buttonCancel      = new Button();
		this.buttonSave        = new Button();
		this.binder            = new Binder<>();
		
		this.setPadding(false);
		this.formLayout.setResponsiveSteps(
			new FormLayout.ResponsiveStep("0px", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
			new FormLayout.ResponsiveStep("500px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP));
		this.labelName.setText("Name");
		this.textFieldName.setRequired(true);
		this.textFieldName.setRequiredIndicatorVisible(true);
		this.textFieldName.setPreventInvalidInput(true);
		this.labelDesc.setText("Description");
		this.textFieldDesc.setPreventInvalidInput(true);
		this.labelCategory.setText("Category");
		this.comboBoxCategory.setRequired(true);
		this.comboBoxCategory.setRequiredIndicatorVisible(true);
		this.comboBoxCategory.setItemLabelGenerator(ItemLabelGeneratorFactory.NonNull(Category::getName));
		this.labelPrice.setText("Unit Price");
		this.bigDecimalPrice.setRequiredIndicatorVisible(true);
		this.labelWeight.setText("Unit Weight");
		this.numberFieldWeight.setRequiredIndicatorVisible(true);
		this.labelStock.setText("Units in stock");
		this.integerFieldStock.setRequiredIndicatorVisible(true);
		this.formItem.getElement().setAttribute("colspan", "2");
		this.upload.setAutoUpload(true);
		this.upload.setMaxFiles(1);
		this.upload.setMaxWidth("");
		this.label.setText("Image upload");
		this.horizontalLayout.setPadding(true);
		this.buttonCancel.setText("Cancel");
		this.buttonSave.setText("Save");
		this.buttonSave.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		
		this.binder.forField(this.textFieldDesc).asRequired().withNullRepresentation("")
			.withValidator(new StringLengthValidator("Description must containt at least 15 characters", 15, null))
			.bind(Product::getDescription, Product::setDescription);
		this.binder.forField(this.bigDecimalPrice).asRequired()
			.withValidator(RangeValidator.of("The price cant be lower than 0.01", new BigDecimal("0.01"), null))
			.bind(Product::getUnitPrice, Product::setUnitPrice);
		this.binder.forField(this.numberFieldWeight).asRequired()
			.withValidator(RangeValidator.of("The weight of the product cant be lower than 0,001", 0.001, null))
			.bind(Product::getUnitWeight, Product::setUnitWeight);
		this.binder.forField(this.integerFieldStock).asRequired()
			.withValidator(RangeValidator.of("The amount of products in stock cant be lower than 0", 0, null))
			.bind(Product::getUnitsInStock, Product::setUnitsInStock);
		this.binder.forField(this.textFieldName).asRequired().withNullRepresentation("").bind(Product::getName,
			Product::setName);
		
		this.labelName.setSizeUndefined();
		this.labelName.getElement().setAttribute("slot", "label");
		this.textFieldName.setWidthFull();
		this.textFieldName.setHeight(null);
		this.formItem2.add(this.labelName, this.textFieldName);
		this.labelDesc.setSizeUndefined();
		this.labelDesc.getElement().setAttribute("slot", "label");
		this.textFieldDesc.setWidthFull();
		this.textFieldDesc.setHeight(null);
		this.formItem3.add(this.labelDesc, this.textFieldDesc);
		this.labelCategory.setSizeUndefined();
		this.labelCategory.getElement().setAttribute("slot", "label");
		this.comboBoxCategory.setWidthFull();
		this.comboBoxCategory.setHeight(null);
		this.formItem4.add(this.labelCategory, this.comboBoxCategory);
		this.labelPrice.setSizeUndefined();
		this.labelPrice.getElement().setAttribute("slot", "label");
		this.bigDecimalPrice.setWidthFull();
		this.bigDecimalPrice.setHeight(null);
		this.formItem5.add(this.labelPrice, this.bigDecimalPrice);
		this.labelWeight.setSizeUndefined();
		this.labelWeight.getElement().setAttribute("slot", "label");
		this.numberFieldWeight.setWidthFull();
		this.numberFieldWeight.setHeight(null);
		this.formItem6.add(this.labelWeight, this.numberFieldWeight);
		this.labelStock.setSizeUndefined();
		this.labelStock.getElement().setAttribute("slot", "label");
		this.integerFieldStock.setWidthFull();
		this.integerFieldStock.setHeight(null);
		this.formItem7.add(this.labelStock, this.integerFieldStock);
		this.upload.setSizeFull();
		this.label.setSizeUndefined();
		this.label.getElement().setAttribute("slot", "label");
		this.formItem.add(this.upload, this.label);
		this.formLayout.add(this.formItem2, this.formItem3, this.formItem4, this.formItem5, this.formItem6,
			this.formItem7,
			this.formItem);
		this.buttonCancel.setWidthFull();
		this.buttonCancel.setHeight(null);
		this.buttonSave.setWidthFull();
		this.buttonSave.setHeight(null);
		this.horizontalLayout.add(this.buttonCancel, this.buttonSave);
		this.horizontalLayout.setVerticalComponentAlignment(FlexComponent.Alignment.START, this.buttonSave);
		this.formLayout.setSizeFull();
		this.horizontalLayout.setWidthFull();
		this.horizontalLayout.setHeight(null);
		this.add(this.formLayout, this.horizontalLayout);
		this.setHorizontalComponentAlignment(FlexComponent.Alignment.START, this.horizontalLayout);
		this.setSizeFull();
		
		this.upload.addSucceededListener(this::upload_onSucceeded);
		this.upload.addFileRejectedListener(this::upload_onFileRejected);
		this.buttonCancel.addClickListener(this::buttonCancel_onClick);
		this.buttonSave.addClickListener(this::buttonSave_onClick);
	} // </generated-code>
	
	// <generated-code name="variables">
	private FormLayout         formLayout;
	private Button             buttonCancel, buttonSave;
	private BigDecimalField    bigDecimalPrice;
	private Upload             upload;
	private NumberField        numberFieldWeight;
	private IntegerField       integerFieldStock;
	private HorizontalLayout   horizontalLayout;
	private Label              labelName, labelDesc, labelCategory, labelPrice, labelWeight, labelStock, label;
	private ComboBox<Category> comboBoxCategory;
	private TextField          textFieldName, textFieldDesc;
	private FormItem           formItem2, formItem3, formItem4, formItem5, formItem6, formItem7, formItem;
	private Binder<Product>    binder;
	// </generated-code>
	
}
